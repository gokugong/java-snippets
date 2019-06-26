/*
 * Copyright &#169; 2018 Manhattan Associates, Inc.  All Rights Reserved.
 *
 * Confidential, Proprietary and Trade Secrets Notice
 *
 * Use of this software is governed by a license agreement. This software
 * contains confidential, proprietary and trade secret information of
 * Manhattan Associates, Inc. and is protected under United States and
 * international copyright and other intellectual property laws. Use, disclosure,
 * reproduction, modification, distribution, or storage in a retrieval system in
 * any form or by any means is prohibited without the prior express written
 * permission of Manhattan Associates, Inc.
 *
 * Manhattan Associates, Inc.
 * 2300 Windy Ridge Parkway, 10th Floor
 * Atlanta, GA 30339 USA
 */
package reflection;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface ReflectionUtil
{
    String EMPTY = "";

    /**
     * Retrieve field value if regular class or invoke method if interface
     *
     * @param t
     * @param fieldMethodName
     * @param <T>
     * @return
     */
    static <T> String getValueByFieldOrMethodName(T t, String fieldMethodName)
    {
        String rv = EMPTY;
        if (t == null || StringUtils.isEmpty(fieldMethodName))
        {
            throw getUnexpectedRuntimeException(MessageFormat.format("Unexpected arguments {0}.{1}", t, fieldMethodName));
        }

        try
        {
            rv = getFieldValue(t, fieldMethodName);
        } catch (RuntimeException e)
        {
            // try if it's a derived field, or defined as method
            Method method = getMethodByName(t, fieldMethodName);
            if (method == null)
            {
                throw getUnexpectedRuntimeException(MessageFormat.format("Method not found {0}.{1}", t, fieldMethodName));
            }
            rv = invokeMethod(t, method);
        }

        return rv;
    }

    static <T> String getFieldValue(T t, String fieldName)
    {
        String rv;
        Field field = getFieldByName(t, fieldName);
        if (field == null)
        {
            throw getUnexpectedRuntimeException(MessageFormat.format("Field not found {0}.{1}", t, fieldName));
        }

        field.setAccessible(true);
        try
        {
            rv = String.valueOf(field.get(t));
        } catch (IllegalAccessException e)
        {
            throw getUnexpectedRuntimeException(MessageFormat.format("Cannot access field {0}.{1}", t.getClass().getName(), field.getName()));
        }
        return rv;
    }

    static <T> Field getFieldByName(T t, String fieldName)
    {
        if (t == null || fieldName == null)
        {
            throw getUnexpectedRuntimeException(MessageFormat.format("Unexpected arguments {0}.{1}", t, fieldName));
        }
        return getAllFields(t).stream().filter(f -> fieldName.equals(f.getName())).findFirst().get();
    }

    static <T> List<Field> getAllFields(T t)
    {
        return getAllFields(t.getClass());
    }

    static List<Field> getAllFields(Class<?> clazz)
    {
        List<Field> fields = new ArrayList<>();
        for (Class<?> c = clazz; c != null && !Object.class.equals(c); c = c.getSuperclass())
        {
            fields.addAll(Arrays.asList(c.getDeclaredFields()));
        }
        return fields;
    }

    static <T> Method getMethodByName(T t, String methodName, Class<?>... parameterTypes)
    {
        if (t == null || methodName == null)
        {
            throw getUnexpectedRuntimeException(MessageFormat.format("Unexpected arguments {0}.{1}", t, methodName));
        }
        Method method = getAllMethods(t).stream().filter(m -> methodName.equals(m.getName()) && Arrays.equals(parameterTypes, m.getParameterTypes())).findFirst().orElse(null);
        if (method == null)
        {
            String params = parameterTypes == null ? EMPTY : parameterTypes.toString();
            if (parameterTypes != null)
                throw getUnexpectedRuntimeException(MessageFormat.format("Cannot find Method by name and arguments {0}.{1}({2})", t, methodName, params));
        }
        return method;
    }

    static <T> List<Method> getAllMethods(T t)
    {
        return getAllMethods(t.getClass());
    }

    static List<Method> getAllMethods(Class<?> clazz)
    {
        List<Method> methods = new ArrayList<>();
        for (Class<?> c = clazz; c != null && !Object.class.equals(c); c = c.getSuperclass())
        {
            methods.addAll(Arrays.asList(c.getDeclaredMethods()));
        }
        return methods;
    }

    static <T> String invokeMethod(T t, Method method, Object... args)
    {
        String rv;
        if (method == null)
        {
            throw getUnexpectedRuntimeException(MessageFormat.format("Unexpected arguments {0}.{1}", t, method));
        }
        method.setAccessible(true);
        try
        {
            rv = String.valueOf(method.invoke(t, args));
        } catch (IllegalAccessException e)
        {
            throw getUnexpectedRuntimeException(MessageFormat.format("Illegal access {0}.{1}", t, method));
        } catch (InvocationTargetException e)
        {
            throw getUnexpectedRuntimeException(MessageFormat.format("Cannot invoke target {0}.{1}", t, method));
        }
        return rv;
    }

    static RuntimeException getUnexpectedRuntimeException(String message)
    {
        return new RuntimeException(message);
    }
}
