package com.example.luoanforum;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@SpringBootTest
class LuoanforumApplicationTests {

//    @Test
    void contextLoads() {
        Pattern compile = Pattern.compile("^\\*(.*)\\*$");
        Matcher matcher = compile.matcher("/resource\\user\\1\\luoan_forum_database.png/");
        if (matcher.find()) System.out.println(matcher.group(1));
    }

    StringBuffer pathSeparator = new StringBuffer();

    private String setPathSeparator(String separator, String ...params) {
        String tempParam = "";
        int len = pathSeparator.length();
        if (len != 0) {
            pathSeparator.delete(0,len);
        }
        for (String param : params) {
            tempParam = param.replaceAll("\\s*", "");
            Pattern compile = Pattern.compile("^" + separator + "*(.*?)" + separator + "*$");
            Matcher matcher = compile.matcher(tempParam);
            if (matcher.find()) pathSeparator.append(matcher.group(1));
            else pathSeparator.append(tempParam);
            pathSeparator.append(separator);
        }
        len = pathSeparator.length();
        int index = -1;
        if ((index = pathSeparator.lastIndexOf(separator)) == len-1)
            pathSeparator.delete(index,len);
//        pathSeparator.delete(0,len);
        return pathSeparator.toString();
    }

//    @Test
    void Text() {
        String a = "    /resource\\user  \\1\\luoan_fo  rum_d  atab ase.png\\///";
        System.out.println(a.replaceAll("\\s*", ""));
        System.out.println(setPathSeparator("/",a));
    }

}
