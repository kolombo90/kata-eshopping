package com.onepoint.kata.eshopping.utilities;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class MyUtils {
    public static Set<String> getAllFields(final Class<?> type) {
        Set<String> fields = new HashSet<String>();
        //loop the fields using Java Reflections
        for (Field field : type.getDeclaredFields()) {
            fields.add(field.getName());
        }

        //recursive call to getAllFields
        if (type.getSuperclass() != null) {
            fields.addAll(getAllFields(type.getSuperclass()));
        }
        return fields;
    }
}
