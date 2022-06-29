package com.wimfra.tourplanner.configuration;

import lombok.Builder;
import lombok.Data;
import java.util.Properties;

@Data
@Builder
public class AppConfiguration {

    private String dbUrl;
    private String dbUsername;
    private String dbPassword;
    private String mapquestKey;

    public static AppConfiguration fromProperties(Properties appProps){
        return AppConfiguration.builder()
                .dbUrl(appProps.getProperty("datasource.url"))
                .dbUsername(appProps.getProperty("datasource.username"))
                .dbPassword(appProps.getProperty("datasource.password"))
                .mapquestKey(appProps.getProperty("mapquest.key"))
                .build();
    }
}
