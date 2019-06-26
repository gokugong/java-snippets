package patterns.builder;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class RotatedRectangleTest
{
    RotatedRectangle rotatedRectangle;
    Rectangle rectangle;
    List<Rectangle> rectangles;
    Map<Integer, Rectangle> rectangleMap;

    @Before
    public void setup()
    {
        rotatedRectangle = RotatedRectangle.builder()
                .theta(Math.PI / 2)
                .width(640)
                .height(400)
                .opacity(0.5d)
                .build();

        rectangle = Rectangle.builder()
                .height(400)
                .opacity(0.5d)
                .build();

        rectangles = Arrays.asList(
                Rectangle.builder().height(400).build(),
                Rectangle.builder().height(300).build(),
                Rectangle.builder().height(600).build(),
                Rectangle.builder().height(200).build(),
                RotatedRectangle.builder().height(100).build()
        );

        //TODO: (not working) rectangleMap = rectangles.stream().collect(Collectors.toMap(Function.identity(), (r) -> r));
    }

    @Test
    public void testBuilder()
    {
        System.out.println(rotatedRectangle.getTheta());
        System.out.println(rotatedRectangle.getWidth());
        System.out.println(rotatedRectangle.getHeight());
        System.out.println(rotatedRectangle.getOpacity());
        assertEquals(Math.PI / 2, rotatedRectangle.getTheta(), 0.00000001d);

    }

    @Test
    public void testShape()
    {
        Shape shape = Shape.builder().opacity(0.4d).build();
        assertEquals(0.4d, shape.getOpacity(), 0.000001d);
    }

    @Test
    public void testCompare()
    {
        List<Rectangle> sorted = new ArrayList<>(rectangles);
        sorted.sort(Rectangle.compareByHeight);
        sorted.forEach(e -> System.out.println(e.getHeight()));
        assertEquals(100d, sorted.get(0).getHeight(), 0.00001d);
        assertEquals(200d, sorted.get(1).getHeight(), 0.00001d);
    }

    @Test
    public void testStream()
    {
        List<Rectangle> sorted = rectangles.stream().sorted(Rectangle.compareByHeight).collect(Collectors.toList());
        sorted.forEach(e -> System.out.println(e.getHeight()));
        assertEquals(300d, sorted.get(2).getHeight(), 0.00001d);
        assertEquals(200d, sorted.get(1).getHeight(), 0.00001d);
    }

    @Test
    public void getClassByName()
    {
        Class<?> clazz = rectangle.getClass();
        assertEquals("Rectangle", clazz.getSimpleName());
        String baseClassName = rectangle.getClass().getSuperclass().getName();
        System.out.println("Base of " + clazz.getSimpleName() + " is " + baseClassName + "/" + clazz.getSuperclass().getSimpleName());
    }
}