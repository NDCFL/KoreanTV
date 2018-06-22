package com.dt.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射工具类<br />
 * 创建于2017-12-18
 *
 * @author 陈飞龙
 * @version 1.0
 */
public class ReflectUtils {

    /**
     * 调用指定属性的getter方法
     * @param obj 对象
     * @param property 属性名
     * @return getter方法的返回值
     */
    @SuppressWarnings({"unchecked"})
    public static Object invokeGetter(Object obj, String property) {
        Class clazz = obj.getClass();
        try {
            Method method = clazz.getMethod(PropertyUtils.getter(property));
            return method.invoke(obj);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 调用指定属性的setter方法
     * @param obj 对象
     * @param property 属性名
     * @param param setter方法的参数
     */
    @SuppressWarnings({"unchecked"})
    public static void invokeSetter(Object obj, String property, Object param) {
        Class clazz = obj.getClass();
        try {
            if (param != null) {
                Method method = clazz.getMethod(PropertyUtils.setter(property), param.getClass());
                method.invoke(obj, param);
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
