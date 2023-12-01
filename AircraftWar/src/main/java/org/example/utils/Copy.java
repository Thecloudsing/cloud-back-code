package org.example.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;

public class Copy {

    private final static String[] basicDataTypes = {"Byte", "Short", "Integer", "Long", "Float", "Double", "Boolean","Char","String",
            "byte", "short", "int", "long", "float", "double", "boolean","char","String"};

    public static <T> T copyObject(Object o, Class<T> a) throws
            NoSuchMethodException,
            InvocationTargetException,
            InstantiationException,
            IllegalAccessException,
            NoSuchFieldException
    {
        T t = isConstructor(o, a);
        if (isBasicDataType(o))
            return (T) o;

        Field[] declaredFieldsT = t.getClass().getDeclaredFields();
        Field[] declaredFieldsO = o.getClass().getDeclaredFields();
        for (int i = 0; i < declaredFieldsO.length; i++) {
            declaredFieldsO[i].setAccessible(true);
            declaredFieldsT[i].setAccessible(true);
            if (declaredFieldsO[i].getName().equals("serialVersionUID"))
                continue;
            if (!isBasicDataType(declaredFieldsO[i])) {
                Object z = declaredFieldsO[i].get(o);
                if (z == null) continue;
                Object copy = copyObject(z, z.getClass());
                declaredFieldsT[i].set(t,copy);
            } else {
                declaredFieldsT[i].set(t,declaredFieldsO[i].get(o));
            }
        }
        return t;
    }
    private static <T> T isConstructor(Object o, Class<T> a) throws
            IllegalAccessException,
            NoSuchMethodException,
            InvocationTargetException,
            InstantiationException,
            NoSuchFieldException
    {
        if (isBasicDataType(o))
            return (T) o;

        Constructor<?>[] constructors = o.getClass().getConstructors();
        Field[] declaredFields = o.getClass().getDeclaredFields();
        Object[] fieldsObject = new Object[declaredFields.length];
        String[] fieldsName = new String[declaredFields.length];
        for (int i = 0; i < declaredFields.length; i++) {
            declaredFields[i].setAccessible(true);
            fieldsObject[i] = declaredFields[i];
            fieldsName[i] = declaredFields[i].getName();
        }
        int[] index = new int[constructors.length];
        int appropriateConstructorLength = 0;
        for (int i = 0; i < constructors.length; i++) {
            Parameter[] parameters = constructors[i].getParameters();
            int count = 0;
            for (Parameter parameter : parameters) {
                for (int j = 0; j < fieldsName.length; j++) {
                    if (parameter.getName().equals(fieldsName[j]) && fieldsObject[j] != null) {
                        count++;
                    }
                }
            }
            if (count == parameters.length) {
                index[i] = i+1;
                appropriateConstructorLength++;
            }
        }
        Class<?>[][] classes = new Class<?>[appropriateConstructorLength][];
        Object[][] objects = new Object[appropriateConstructorLength][];
        for (int i = 0; i < constructors.length; i++) {
            if (index[i] == i+1) {
                Parameter[] parameters = constructors[i].getParameters();
                classes[i] = new Class[parameters.length];
                objects[i] = new Object[parameters.length];
                for (int j = 0; j < parameters.length; j++) {
                    classes[i][j] = parameters[j].getType();
                    Field declaredField = o.getClass().getDeclaredField(parameters[j].getName());
                    declaredField.setAccessible(true);
                    objects[i][j] = declaredField.get(o);
                }
            }
        }
        for (int i = 0; i < classes.length; i++) {
            for (int j = 0; j < classes.length - i - 1; j++) {
                if (classes[j].length < classes[j+1].length) {
                    Class<?>[] tempClasses = classes[j];
                    classes[j] = classes[j+1];
                    classes[j+1] = tempClasses;

                    Object[] tempObjects = objects[j];
                    objects[j] = objects[j+1];
                    objects[j+1] = tempObjects;
                }
            }
        }
        if (classes.length == 0 || classes[0] == null || classes[0].length == 0)
            return a.getConstructor().newInstance();
        Constructor<T> constructor = a.getConstructor(classes[0]);
        Object[] objectsCopy = new Object[objects[0].length];
        for (int i = 0; i < objects[0].length; i++) {
            objectsCopy[i] = copyObject(objects[0][i],objects[0][i].getClass());
        }
        return constructor.newInstance(objectsCopy);
    }

    private static boolean isBasicDataType(Object obj) {
        for (String type: basicDataTypes) {
            if (obj.getClass().getSimpleName().equals(type)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isBasicDataType(Field field) {
        for (String type: basicDataTypes) {
            if (field.getType().getSimpleName().equals(type)) {
                return true;
            }
        }
        return false;
    }

    /*

        <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <parameters>true</parameters>
                </configuration>
            </plugin>
        </plugins>
    </build>

     */
}
