package com.eq2008;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SystemConf {

    private static Properties properties = new Properties();

  

    static {
        try {
            InputStream in = SystemConf.class.getClassLoader().getResourceAsStream("res\\EQ2008_Dll_Set.ini");
            properties.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    public static void set(String key, String value) {
        properties.setProperty(key, value);
    }
}
