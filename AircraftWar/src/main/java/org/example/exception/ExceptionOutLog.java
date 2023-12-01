package org.example.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Component
public class ExceptionOutLog {

    private static final Map<String,PrintWriter> printWriterMap = new HashMap<>();
    @Value("${logs_path}")
    private String path;
    public void print(Object obj, Exception e) {
        String name = obj.getClass().getSimpleName();
        if (!printWriterMap.containsKey(name)) {
            try {
                BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(this.path + name + ".log"));
                PrintWriter printWriter = new PrintWriter(bufferedWriter,true);
                printWriterMap.put(name,printWriter);
            } catch (IOException exception) {

            }
        }

        PrintWriter printWriter = printWriterMap.get(name);
        e.printStackTrace(printWriter);
        printWriter.flush();
    }

    @PreDestroy
    private void destroy() {
        printWriterMap.forEach((k,v) -> {
            v.close();
        });
    }
}
