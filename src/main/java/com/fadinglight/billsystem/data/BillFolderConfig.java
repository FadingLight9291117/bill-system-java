package com.fadinglight.billsystem.data;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "bill-folder")
@Data
public class BillFolderConfig {
    private String path;
}
