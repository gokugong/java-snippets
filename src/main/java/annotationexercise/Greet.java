package annotationexercise;

/**
 * See {@link <a href="https://rationaleemotions.wordpress.com/2016/05/27/changing-annotation-values-at-runtime/">Changing Annotation value at runtime</a>}
 */


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Greet
{
    String name() default "";
}
