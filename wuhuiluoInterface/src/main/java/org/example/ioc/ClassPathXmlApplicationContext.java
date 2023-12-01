package org.example.ioc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ClassPathXmlApplicationContext {
    //IOC容器
    private final Map<String, Object> beanMap = new HashMap<>();

    public ClassPathXmlApplicationContext(String path, String elementName) {

        try {
            //InputStream inputStream = getClass().getClassLoader().getResourceAsStream("applicationContext.xml");
            String path_ = Objects.requireNonNull(getClass().getClassLoader().getResource(path)).getPath();
            //读取配置文件
            InputStream inputStream = Files.newInputStream(Paths.get(path_.substring(1)));
            //1.创建DocumentBuilderFactory
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            //2.创建DocumentBuilder对象
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder() ;
            //3.创建Document对象
            Document document = documentBuilder.parse(inputStream);

            //获取<beans>标签下的所有子节点
            NodeList beanNodeList = document.getElementsByTagName(elementName);
            //注册类
            for (int i = 0; i < beanNodeList.getLength(); i++) {
                Node beanNode = beanNodeList.item(i);
                if (beanNode.getNodeType() == Node.ELEMENT_NODE) {
                    //Node转换为Element方便获取参数
                    Element beanElement = (Element) beanNode;
                    //获取bean标签配置的id参数
                    String beanId = beanElement.getAttribute("id");
                    //获取bean标签配置的class参数
                    String className = beanElement.getAttribute("class");
                    //通过反射的方式注册一个配置类到HashMap容器
                    Object beanObj = Class.forName(className).newInstance();
                    beanMap.put(beanId, beanObj);
                }
            }
            //注入依赖
            for (int i = 0; i < beanNodeList.getLength(); i++) {
                //获取beans的子节点
                Node beanNode = beanNodeList.item(i);
                if (beanNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element beanElement = (Element) beanNode;
                    //获取需要注入依赖的beanId
                    String beanId = beanElement.getAttribute("id");
                    //获取bean的所有子节点
                    NodeList beanChildNodes = beanElement.getChildNodes();
                    for (int j = 0; j < beanChildNodes.getLength(); j++) {
                        //获取bean的节点
                        Node beanChildNode = beanChildNodes.item(j);
                        if (beanChildNode.getNodeType() == Node.ELEMENT_NODE) {
                            //Node转换为Element方便获取参数
                            Element propertyElement = (Element) beanChildNode;
                            //获取子标签配置的name参数  -- 需要注入属性的名字
                            String propertyName = propertyElement.getAttribute("name");
                            //获取子标签配置的ref参数  -- 依赖类
                            String propertyRef = propertyElement.getAttribute("ref");
                            //从beanMap获取需要的依赖类
                            Object refObj = beanMap.get(propertyRef);
                            //从beanMap获取需要被注入依赖的类
                            Object beanObj = beanMap.get(beanId);
                            //获取需要被注入依赖的类的Class对象
                            Class<?> beanClass = beanObj.getClass();
                            //调用封装的注入依赖的方法  --在下面
                            setField(beanClass, beanObj, propertyName, refObj);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("IOC启动失败", e);
        }
    }

    public void setField(Class<?> beanClass, Object beanObj, String propertyName, Object refObj)
            throws NoSuchFieldException , IllegalAccessException {
        try {
            //根据属性名字拿到属性的反射方法 //找不到会抛出异常被下面的捕获，下面调用class的父类
            Field declaredField = beanClass.getDeclaredField(propertyName);
            //开启暴力反射以便private修饰的属性可以访问
            declaredField.setAccessible(true);
            //注入依赖
            declaredField.set(beanObj, refObj);
        } catch (NoSuchFieldException e) {
            Class<?> aClass = null;
            //获取Class的父类 //没有则抛出异常终止程序
            if ((aClass = beanClass.getSuperclass()) == null)
                throw new NoSuchFieldException(e.getMessage());
            //递归调用
            setField(aClass, beanObj, propertyName, refObj);
        }
    }
    public Object getBean(String id) {
        return beanMap.get(id);
    }
}
