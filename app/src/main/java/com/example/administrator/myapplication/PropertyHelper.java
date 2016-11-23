package com.example.administrator.myapplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Administrator on 2016/11/17.
 */

public class PropertyHelper {
    private static Properties properties;
    public static Object getValue(String  key){
        if(properties == null){
            properties = loadConfig("config.properties");
        }

        if(properties != null){
            return properties.get(key);
        }
        return "";
    }
    private static Properties loadConfig(String file) {
        Properties properties = new Properties();
        try {
            File fil = new File(file);
            FileInputStream s;
            if (!fil.exists()) {

                InputStream in = PropertyHelper.class.getResourceAsStream(file);
                properties.load(in);
                return  properties;
            }
             else
            {
                s = new FileInputStream(file);
            }
            properties.load(s);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return properties;
    }

    private boolean saveConfig(String file, Properties properties) {
        try {
            File fil = new File(file);
            if (!fil.exists()) {
                fil.createNewFile();
            }
            FileOutputStream s = new FileOutputStream(fil);
            properties.store(s, "");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
