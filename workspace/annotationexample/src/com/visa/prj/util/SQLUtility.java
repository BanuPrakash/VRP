package com.visa.prj.util;

import com.visa.prj.annotations.Column;
import com.visa.prj.annotations.Table;

import java.lang.reflect.Method;

public class SQLUtility {
    // Book.class
    public static String createStatement(Class<?> clazz) {
        StringBuilder builder = new StringBuilder();
        Table table = clazz.getAnnotation(Table.class);
        if(table != null) {
            builder.append("create table ");
            builder.append(table.name());
            builder.append("(");
            Method[] methods = clazz.getDeclaredMethods();
            for(Method m : methods) {
                if(m.getName().startsWith("get")) {
                    Column col = m.getAnnotation(Column.class);
                    if(col != null) {
                        builder.append(col.name());
                        builder.append(" ");
                        builder.append(col.type());
                        builder.append(",");
                    }
                }
            }
        }
        builder.setCharAt(builder.lastIndexOf(","), ')');
        return builder.toString();
    }

    public static String insertStatement(Object obj) {
        Class<?> clazz = obj.getClass();
        return null;
    }
}
