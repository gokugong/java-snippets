package reflection;

import org.junit.Test;
import patterns.builder.Rectangle;
import patterns.builder.Shape;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class ReflectionUtilTest
{

    @Test
    public void getFieldByName()
    {
        Object o = Rectangle.builder().height(100.0d).width(2.0d).opacity(10.0d).build();
        assertEquals("Rectangle", o.getClass().getSimpleName());

        Method[] methods = Rectangle.class.getDeclaredMethods();
        Method method = null;
        try
        {
            method = Rectangle.class.getMethod("area", o.getClass());
        } catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        }
        System.out.println("Rectangle.area.parameters=" + method.getParameterTypes());
        try
        {
            System.out.println("Rectangle.area=" + method.invoke(null, o));
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        } catch (InvocationTargetException e)
        {
            e.printStackTrace();
        }
        ReflectionUtil.getAllMethods(o).stream().forEach(m -> System.out.println(m.getParameterTypes()));
        System.out.println("Rectangle.opacity=" + ReflectionUtil.getFieldByName(o, "opacity"));
        assertEquals("10.0", ReflectionUtil.getValueByFieldOrMethodName(o, "opacity"));
        assertEquals("10.0", ReflectionUtil.getValueByFieldOrMethodName(o, "getOpacity"));
        assertEquals("opacity", ReflectionUtil.getFieldByName(patterns.builder.Rectangle.builder().height(400).build(), "opacity").getName());
    }

    @Test
    public void getAllFields()
    {
        System.out.println("Shape.fields = [" + ReflectionUtil.getAllFields(Shape.class) + "]");
        System.out.println("Rectangle.fields = [" + ReflectionUtil.getAllFields(Rectangle.class) + "]");
    }
}