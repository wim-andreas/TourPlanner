package com.wimfra.tourplanner.configuration;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppConfigurationTest {

    private static AppConfiguration appConfiguration;

    @BeforeAll
    public static void setUp(){
        appConfiguration = AppConfigurationLoader.getInstance().getAppConfiguration();
    }

    @Test
    public void getMapKeyTest(){
        assertEquals("QX46KJZxJ9svMGvYSVPiCop9EHGTjUIc", appConfiguration.getMapquestKey());
    }

    @Test
    public void getURLTest(){
        assertEquals("jdbc:postgresql://localhost:5432/postgres", appConfiguration.getDbUrl());
    }

    @Test
    public void getUsernameTest(){
        assertEquals("swe2user", appConfiguration.getDbUsername());
    }

    @Test
    public void getPasswordTest(){
        assertEquals("swe2pw", appConfiguration.getDbPassword());
    }

 
}
