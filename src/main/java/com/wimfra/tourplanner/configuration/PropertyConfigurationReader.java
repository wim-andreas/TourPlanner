package com.wimfra.tourplanner.configuration;

import com.wimfra.tourplanner.logger.ILoggerWrapper;
import com.wimfra.tourplanner.logger.LoggerFactory;
import com.wimfra.tourplanner.viewmodel.AddLogViewModel;

import java.util.Properties;

public class PropertyConfigurationReader implements AppConfigurationReader {

    private static final ILoggerWrapper logger = LoggerFactory.getLogger(PropertyConfigurationReader.class);
    private final Properties appProps = new Properties();
    private boolean initialized = false;

    @Override
    public AppConfiguration getAppConfiguration() {
        if(!initialized){
            try {
                logger.debug("Reading properties from file");
                appProps.load(Thread.currentThread()
                        .getContextClassLoader()
                        .getResourceAsStream("configuration.properties"));
            } catch (Exception e) {
                logger.error("Failed to load configuration.properties, please make sure there is a file 'configuration.properties' available");
                System.exit(1);
            }
            initialized = true;
        }
        return AppConfiguration.fromProperties(appProps);
    }
}
