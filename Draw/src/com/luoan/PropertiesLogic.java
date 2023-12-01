package com.luoan;

import com.luoan.view.Interface;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Properties;

public class PropertiesLogic {
    public PropertiesLogic(Interface i) throws IOException {
        this.init(i);
    }
    private final String path = System.getProperty("user.dir");
    private void createProperties() throws IOException {
        Properties properties = new Properties();
        properties.put("DrawRandom","70");
        OutputStream os = Files.newOutputStream(Paths.get(path + "\\config.properties"));
        properties.store(os,"config");
        os.flush();
        os.close();
    }

    private void createDrawConf() throws IOException {
        BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(Files.newOutputStream(
                new File(path + "\\idiom.conf").toPath()), StandardCharsets.UTF_8));
        for (int i = 0; i < 50; i++) {
            bw.write(i + "=\n");
        }
        bw.flush();
        bw.close();
    }
    private void init(Interface inter) throws IOException {

        Properties properties = new Properties();
        File configFile = new File(path + "\\config.properties");
        if (!configFile.exists()) {
            System.out.println("config.properties not found");
            System.out.println("create config.properties ...");
            this.createProperties();
        }
        InputStream is = Files.newInputStream(configFile.toPath());
        properties.load(is);
        String drawRandom = properties.getProperty("DrawRandom");
        inter.setDataSize(Integer.parseInt(drawRandom));
        is.close();
        File drawFile = new File(path + "\\idiom.conf");
        if (!drawFile.exists()) {
            System.out.println("Draw.conf not found");
            System.out.println("create Draw.conf ...");
            this.createDrawConf();
        }
        BufferedReader br = new BufferedReader(
                new InputStreamReader(Files.newInputStream(drawFile.toPath()), StandardCharsets.UTF_8));
        String line;
        String[] split;
        int i = 1;
        while ((line = br.readLine()) != null) {
            split = line.split("=");
            if (split.length == 2)
                Interface.data.add(" " + split[1]);

        }
        br.close();
    }
}
