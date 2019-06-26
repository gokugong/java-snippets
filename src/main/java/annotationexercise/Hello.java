package annotationexercise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

@Greet(name = "Darth Vader")
public class Hello
{
    private static final Logger logger = LoggerFactory.getLogger(Hello.class);
    private static final String ANNOTATIONS = "annotations";
    private static final String ANNOTATION_DATA = "annotationData";

    public static void hello()
    {
        String greeting = Hello.class.getAnnotation(Greet.class).name();
        logger.debug("Hello from {}", greeting);
    }

    public static void alterAnnotaionValue(Class clazz, Class<? extends Annotation> annotationToAlter, Annotation annotationValue)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException
    {
        //In JDK8 Class has a private method called annotationData().
        //We first need to invoke it to obtain a reference to AnnotationData class which is a private class
        Method method = Class.class.getDeclaredMethod(ANNOTATION_DATA, null);
        method.setAccessible(true);
        //Since AnnotationData is a private class we cannot create a direct reference to it. We will have to
        //manage with just Object
        Object annotationData = method.invoke(clazz);
        //We now look for the map called "annotations" within AnnotationData object.
        Field annotations = annotationData.getClass().getDeclaredField(ANNOTATIONS);
        annotations.setAccessible(true);
        Map<Class<? extends Annotation>, Annotation> map =
                (Map<Class<? extends Annotation>, Annotation>) annotations.get(annotationData);
        map.put(annotationToAlter, annotationValue);
    }

    public static void main(String[] args) throws Exception
    {
        hello();
        alterAnnotaionValue(Hello.class, Greet.class, new DynamicGreet("Darth Maul"));
        hello();
    }
}
