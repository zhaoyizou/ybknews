package com.visionet.core.util.file;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ArrayUtils;


/** 
 * @ClassName: ImagesUtils 
 * @Description: TODO: 图片工具类
 * @author liusy@visionet.com.cn
 * @date 2015年3月18日 下午2:00:35 
 *  
 */
public class ImagesUtils {

    private static final String[] IMAGES_SUFFIXES = {
            "bmp", "jpg", "jpeg", "gif", "png", "tiff"
    };

    /** 
     * @Title: isImage 
     * @Description: TODO:是否是图片
     * @param @param filename
     * @param @return    设定文件 
     * @return boolean    返回类型 
     * @throws 
     */
    public static boolean isImage(String filename) {
        if (filename == null || filename.trim().length() == 0) return false;
        return ArrayUtils.contains(IMAGES_SUFFIXES, FilenameUtils.getExtension(filename).toLowerCase());
    }

}
