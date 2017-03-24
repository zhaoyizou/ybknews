package com.visionet.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/** 
 * @ClassName: ConfigManager 
 * @Description: TODO: properties文件处理工具类
 * @author liusy@visionet.com.cn
 * @date 2015年3月18日 上午11:09:11 
 *  
 */
public class ConfigManager {

    private static final String _FILE_PATH = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" +
            File.separator + "persistence-mysql" +
            ".properties";
    private Long _lastModifiedTime = 0l;
    private Properties _props = null;
    private File _file = null;

    private ConfigManager() {
        _file = new File(_FILE_PATH);
        _lastModifiedTime = _file.lastModified();
        if (_lastModifiedTime == 0) {
            System.err.println(_FILE_PATH + " file does not exist!");
        }
        _props = new Properties();
        try {
            _props.load(new FileInputStream(_file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ConfigManager _configManager = new ConfigManager();

    synchronized public static ConfigManager getInstance() {
        return _configManager;
    }

    final public Object getConfigItem(String key, Object defaultValue) {
        long newTime = _file.lastModified();
        if (newTime == 0) {
            if (_lastModifiedTime == 0) {
                System.err.println(_FILE_PATH + " file does not exist!");
            } else {
                System.err.println(_FILE_PATH + " file was delete!");
            }
            return defaultValue;
        } else if (newTime > _lastModifiedTime) {//文件已修改重新载入
            _props.clear();
            try {
                _props.load(new FileInputStream(_file));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        _lastModifiedTime = newTime;
        Object val = _props.getProperty(key);
        if (val == null) {
            return defaultValue;
        } else {
            return val;
        }
    }
}
