package com.micro.mysegmentdefault.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * author : micro_hx <p>
 * desc : Class帮助类 <p>
 * email: javainstalling@163.com <p>
 * date : 2017/3/19 - 23:33 <p>
 * interface :
 */

public class ClassUtils {

    public static <T> T getT(Object o, int i) {

        try {
            return ((Class<T>) ((ParameterizedType)
                    (o.getClass().getGenericSuperclass())).getActualTypeArguments()[i]).newInstance();
        } catch (InstantiationException e) {
            //e.printStackTrace();
        } catch (IllegalAccessException e) {
            // e.printStackTrace();
        } catch (ClassCastException e) {
            // e.printStackTrace();
        }
        return null;
    }


    public static Class<?> forName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static ParameterizedType type(final Class raw, final Type... types) {
        return new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return types;
            }

            @Override
            public Type getRawType() {
                return raw;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        } ;
    }







}
