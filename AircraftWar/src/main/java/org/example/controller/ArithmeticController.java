package org.example.controller;

import org.example.agreement.StreamingMedia;
import org.example.agreement.TCP_Type_Server;
import org.example.agreement.TypeProcessing;
import org.example.exception.ExceptionOutLog;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;

import javax.imageio.IIOException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ArithmeticController implements ApplicationContextAware {

    @Autowired
    private ExceptionOutLog exception;
    private final Map<TCP_Type_Server, TypeProcessing> TYPE_PROCESSING_MAP = new HashMap<>();

//    {
//        String packageName = getClass().getPackage().getName();
//        String packageSimpleName = packageName.substring(0, packageName.lastIndexOf(".controller")) + ".service.";
//        for (TCP_Type type : TCP_Type.values()) {
//            try {
//                Class<?> aClass = Class.forName(packageSimpleName + type.value);
//                TypeProcessing o = (TypeProcessing) aClass.newInstance();
//                TYPE_PROCESSING_MAP.put(type,o);
//            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
//                new ExceptionOutLog().print(getClass().getSimpleName(),e);
//            }
//        }
//    }
    public void logic(TCP_Type_Server type, StreamingMedia streamingMedia) {
        try {
            this.TYPE_PROCESSING_MAP.get(type).main(streamingMedia);
        } catch (IOException e) {
            exception.print(this, e);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        for (TCP_Type_Server type : TCP_Type_Server.values()) {
            this.TYPE_PROCESSING_MAP.put(type,applicationContext.getBean(type.value,TypeProcessing.class));
        }
        TYPE_PROCESSING_MAP.forEach((typeServer, typeProcessing) -> System.out.println(typeServer + "=" + typeProcessing));
    }
}
