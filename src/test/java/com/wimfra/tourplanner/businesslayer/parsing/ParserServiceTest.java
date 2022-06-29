package com.wimfra.tourplanner.businesslayer.parsing;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserServiceTest {

    private static ParserService parserService;

    @BeforeAll
    public static void setUp(){
        parserService = new ParserServiceImpl();
    }

    @Test
    public void parseStringIntoDoubleTest(){
        String doubleString = "123.45";
        double parsedString = 0.0;

        parsedString = parserService.parseStringIntoDouble(doubleString);

        assertEquals(123.45, parsedString);
    }

    @Test
    public void parseDoubleIntoString(){
        double value = 2334.324;
        String parsedDouble = "";

        parsedDouble = parserService.parseDoubleIntoString(value);

        assertEquals("2334.324", parsedDouble);
    }
}
