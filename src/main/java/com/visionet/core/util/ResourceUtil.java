package com.visionet.core.util;

import org.apache.commons.lang3.StringUtils;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class ResourceUtil {

    public static String getValueBykey(String fileName, String key) {
        String str = null;
        if (null == key)
            return null;
        try {
            PropertyResourceBundle res = (PropertyResourceBundle) ResourceBundle.getBundle(fileName);
            str = res.getString(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;

    }

    /**
     * 返回带有默认值key
     *
     * @param fileName
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getValueBykey(String fileName, String key, String defaultValue) {
        if (StringUtils.isBlank(fileName)) {
            return defaultValue;
        }
        if (StringUtils.isBlank(key)) {
            return defaultValue;
        }

        try {
            PropertyResourceBundle res = (PropertyResourceBundle) ResourceBundle.getBundle(fileName);
            return res.getString(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

}
