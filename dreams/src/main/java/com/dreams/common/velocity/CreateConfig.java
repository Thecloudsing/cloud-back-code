package com.dreams.common.velocity;

import lombok.Builder;
import lombok.Data;

/**
 * Description:
 *
 * @author luoan
 * @since 2023/11/29
 */

@Data
@Builder
public class CreateConfig {
    private String url;
    private String username;
    private String password;
    private String finalProjectPath;
    private String parentPackage;
    private String moduleName;
    private String[] tables;
}
