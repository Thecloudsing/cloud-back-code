package org.example.mvc;

import com.alibaba.fastjson.JSONObject;
import org.example.ioc.ClassPathXmlApplicationContext;
import org.example.service.ServiceException;
import org.example.utils.Result;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {

    private ClassPathXmlApplicationContext beanFactory;
    private Interceptor interceptor;

    public DispatcherServlet() {
    }

    public void init() throws ServletException {
        super.init();
        //之前是在此处主动创建IOC容器的
        //现在优化为从application作用域去获取
        ServletContext application = getServletContext();
        Object beanFactoryObj = application.getAttribute("beanFactory");
        Object interceptorObj = application.getAttribute("interceptor");
        if (interceptorObj != null) {
            interceptor = (Interceptor) interceptorObj;
        } else {
            throw new RuntimeException("Interceptor容器获取失败！");
        }

        if (beanFactoryObj != null) {
            beanFactory = (ClassPathXmlApplicationContext) beanFactoryObj;
        } else {
            throw new RuntimeException("IOC容器获取失败！");
        }
    }


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if (request.getHeader("access-control-request-method") != null) return;
        Object controllerBeanObj = null; String operate = null;
        try {

            controllerBeanObj = interceptor.preHandle(request, response, beanFactory);
            if (controllerBeanObj instanceof Boolean && (Boolean) controllerBeanObj) return;
            operate = request.getParameter("operate");

            Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (operate.equals(method.getName())) {
                    //1.统一获取请求参数
                    try {
                        interceptor.isUID(request, response, method);
                    } catch (ServiceException e) {
                        response.getWriter().println(JSONObject.toJSONString(Result.Redirect(301, Interceptor.rootPath + "/#/sign", "没有登录请登录")));
                        return;
                    }
                    //1-1.获取当前方法的参数，返回参数数组
                    Parameter[] parameters = method.getParameters();
                    //1-2.parameterValues 用来承载参数的值
                    Object[] parameterValues = new Object[parameters.length];

                    //String[] methodParameterNamesByAnnotation = ParameterNameUtils.getMethodParameterNamesByAnnotation(method);
                    for (int i = 0; i < parameters.length; i++) {
                        Parameter parameter = parameters[i];
                        String parameterName = parameter.getName();
                        //String parameterName = methodParameterNamesByAnnotation[i] ;
                        if ("request".equals(parameterName)) {
                            parameterValues[i] = request;
                        } else if ("response".equals(parameterName)) {
                            parameterValues[i] = response;
                        }
                    }
//                        parameterValues[0] = request;
//                        parameterValues[1] = response;
                    method.invoke(controllerBeanObj, parameterValues);
                    break;
                }
            }
            interceptor.postHandle(request, response);
        } catch (DispatcherServletException e) {
            e.addSuppressed(new DispatcherServletException(operate + "在运行过程出异常"));
            e.printStackTrace();
            afterCompletion(request, response, e);
            response.sendError(404, "找不到路径");
        } catch (ServiceException e) {
            e.addSuppressed(new DispatcherServletException(operate + "在运行过程出异常"));
            e.printStackTrace();
            afterCompletion(request, response, e);
            response.sendError(400, "非法请求参数");
        } catch (Exception e) {
            e.addSuppressed(new DispatcherServletException(operate + "在运行过程出异常"));
            e.printStackTrace();
            afterCompletion(request, response, e);
            response.sendError(500, "服务器异常");
        }
    }

    private void afterCompletion(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        try {
            interceptor.afterCompletion(request, response, ex);
        } catch (Exception e) {
            new DispatcherServletException("DispatcherServlet出错了...", ex).printStackTrace();
        }
    }
}
