package com.wimfra.tourplanner.businesslayer.parsing;

public interface ParserService {

    public Double parseStringIntoDouble(String value);

    public String parseDoubleIntoString(Double value);

    Integer parseStringIntoInt(String value);
}
