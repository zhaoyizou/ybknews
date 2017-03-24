package com.visionet.core.util.spring;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/** 
 * @ClassName: ReflectUtils 
 * @Description: TODO: 反射工具类,用来获取指定类具体泛型类
 * @author liusy@visionet.com.cn
 * @date 2015年3月18日 上午11:08:05 
 *  
 */
public class ReflectUtils {
    /**
     * 得到指定类型的指定位置的泛型实参
     *
     * @param clazz
     * @param index
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
	public static <T> Class<T> findParameterizedType(Class<?> clazz, int index) {
        Type parameterizedType = clazz.getGenericSuperclass();
        //CGLUB subclass target object(泛型在父类上)
        if (!(parameterizedType instanceof ParameterizedType)) {
            parameterizedType = clazz.getSuperclass().getGenericSuperclass();
        }
        if (!(parameterizedType instanceof ParameterizedType)) {
            return null;
        }
        Type[] actualTypeArguments = ((ParameterizedType) parameterizedType).getActualTypeArguments();
        if (actualTypeArguments == null || actualTypeArguments.length == 0) {
            return null;
        }
        return (Class<T>) actualTypeArguments[0];
    }
}
