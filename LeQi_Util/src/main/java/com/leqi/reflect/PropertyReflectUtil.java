/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leqi.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.PropertyUtils;

/**
 *
 * @author duyi
 */
public class PropertyReflectUtil {

    public static Object getValue(Object obj, String attributeName) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (attributeName == null || attributeName.isEmpty()) {
            return obj;
        }

        return PropertyUtils.getProperty(obj, attributeName);
//        Field field = AttrbuteReflectUtil.getDeclaredField(obj.getClass(), attributeName);
//        if (field == null) {
//            return null;
//        }
//        field.setAccessible(true);
//        return field.get(obj);
    }

    public static Object setValue(Object obj, String attributeName, Object value) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (attributeName == null || attributeName.isEmpty()) {
            return value;
        }
        PropertyUtils.setProperty(obj, attributeName, value);
        return obj;
//        Field field = AttrbuteReflectUtil.getDeclaredField(obj.getClass(), attributeName);
//        if (field == null) {
//            return obj;
//        }
//        field.setAccessible(true);
//        field.set(obj, value);
//        return obj;
    }
}
