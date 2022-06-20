package com.wimfra.tourplanner.businesslayer.parsing;

public class ParserServiceImpl implements ParserService {
    //TODO: Write functions that parse the Strings from the StringProperties into other values to be stored in the database

    @Override
    public Double parseStringIntoDouble(String value) {
        Double newDouble;
        if((value.matches("([0-9])*")) || value.matches("([0-9])*\\.([0-9])+")){
             newDouble = Double.parseDouble(value);
        }
        else{
            throw new RuntimeException();
        }
        return newDouble;
    }

    @Override
    public String parseDoubleIntoString(Double value) {
        String newString = String.valueOf(value);
        return newString;
    }
}