package annotationexercise;

import java.lang.annotation.Annotation;

public class DynamicGreet implements Greet
{
    private String name;

    public DynamicGreet(String name)
    {
        this.name = name;
    }

    @Override
    public String name()
    {
        return name;
    }

    @Override
    public Class<? extends Annotation> annotationType()
    {
        return DynamicGreet.class;
    }
}
