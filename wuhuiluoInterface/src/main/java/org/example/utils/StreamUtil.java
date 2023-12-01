package org.example.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamUtil {
    public static String parseObjectString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        int len = 0;
        char[] chars = new char[1024];
        StringBuilder json_data = new StringBuilder();
        while ((len = bufferedReader.read(chars)) != -1) {
            json_data.append(new String(chars, 0, len));
        }
        bufferedReader.close();
        return json_data.toString();
    }
}
