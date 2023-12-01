package com.dreams.common.velocity;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.sql.Types;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @author luoan
 * @since 2023/10/19
 */
public class Main {
    private static final String url = "jdbc:mysql://localhost:3306/campus";
    private static final String username = "root";
    private static final String password = "331520";
    private static final String finalProjectPath = "C:/Users/17561/Downloads/Compressed/dreams";
    private static final String parentPackage = "com.dreams";
    private static final String moduleName = "text";
    public static void main(String[] args) {
        entity();
    }
    private static void entity() {
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("luoan")
                            .commentDate("yyyy/MM/dd")// 设置作者
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(finalProjectPath + "/src/main/java"); // 指定输出目录
                })
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.SMALLINT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);

                }))
                .packageConfig(builder -> {
                    builder.parent(parentPackage) // 设置父包名
                            .moduleName(moduleName) // 设置父包模块名
                            .service("dao") // 设置service包名
                            .serviceImpl("dao.impl") // 设置serviceImpl包名
                            .controller("controller") // 设置controller包名
                            .entity("domain.entity") // 设置entity包名
                            .mapper("mapper") // 设置mapper包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "C:/Users/17561/Downloads/Compressed/dreams/src/main/resources/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("t_news") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀

                    builder.entityBuilder()
                            .enableLombok()
                            .enableTableFieldAnnotation(); // 设置使用lombok

                    builder.serviceBuilder()
                            .formatServiceImplFileName("%sDaoImpl")
                            .formatServiceFileName("%sDao"); // 设置service接口名格式

                })
                .templateEngine(new VelocityTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
    public static void dto_vo() {
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("abc") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .disableOpenDir() //禁止打开输出目录
                            .outputDir(finalProjectPath + "/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(parentPackage) // 设置父包名
                            .moduleName(moduleName) // 设置父包模块名
                            .entity("domain.entity") //设置entity包名
//                            .other("domain.dto") // 设置dto包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, finalProjectPath + "/src/main/resources/mapper")); // 设置mapperXml生成路径

                })
                .injectionConfig(consumer -> {
                    Map<String, String> customFile = new HashMap<>();
                    // DTO
                    customFile.put("DTO.java", "/templates/entityDTO.java.ftl");
                    consumer.customFile(customFile);
                });
    }
}
