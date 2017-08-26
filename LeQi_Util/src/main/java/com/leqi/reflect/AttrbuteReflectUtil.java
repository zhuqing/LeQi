/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leqi.reflect;

import java.lang.reflect.Field;

/**
 *
 * @author duyi
 */
public class AttrbuteReflectUtil {

    public static Object getValue(Object obj, String attributeName) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        if (obj == null) {
            return null;
        }
        if (attributeName == null || attributeName.isEmpty()) {
            return obj;
        }
        Class claz = obj.getClass();
        Field field = AttrbuteReflectUtil.getDeclaredField(claz, attributeName);
        if (field == null) {
            return null;
        }
        field.setAccessible(true);
        return field.get(obj);
    }

    /**
     * 循环向上转型, 获取对象的 DeclaredField
     *
     * @param object : 子类对象
     * @param fieldName : 父类中的属性名
     * @return 父类中的属性对象
     */
    public static Field getDeclaredField(Class<?> clazz, String fieldName) {
        Field field = null;
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                return field;
            } catch (Exception e) {
                
            }
        }
        return null;
    }

}
