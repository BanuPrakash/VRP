package com.visa.prj.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

public class MobileDaoFactory {
    private static String MOBILE_CLASS = "";
    static  {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("config");
        MOBILE_CLASS = resourceBundle.getString("MOBILE_DAO");
    }
    public static MobileDao getMobileDao() {
//        return new MobileDaoDbImpl();

        try {
            return (MobileDao) Class.forName(MOBILE_CLASS).getConstructor().newInstance();
        }  catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
