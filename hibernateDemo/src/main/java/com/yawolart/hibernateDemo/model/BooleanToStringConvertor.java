package com.yawolart.hibernateDemo.model;

import jakarta.persistence.AttributeConverter;

public class BooleanToStringConvertor implements
        AttributeConverter<Boolean,String> {

    @Override
    public Boolean convertToEntityAttribute(String s) {
        if(s == null) return null;
        return s.equals("yes");
    }

    @Override
    public String convertToDatabaseColumn(Boolean aBoolean) {
        if(aBoolean == null) return null;
        return aBoolean?"yes":"no";
    }
}
