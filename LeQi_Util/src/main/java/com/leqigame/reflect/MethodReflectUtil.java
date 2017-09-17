/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leqigame.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zhuqing
 */
public class MethodReflectUtil {

    public static Method getMethod(Class claz, String methodName, Class... parameters) {
        try {
            Method method = claz.getMethod(methodName, parameters);
            method.setAccessible(true);
            return method;
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(MethodReflectUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(MethodReflectUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static Object executeMethod(Method method, Object entity, Object... paramenters) {
        try {
            return method.invoke(entity, paramenters);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(MethodReflectUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
