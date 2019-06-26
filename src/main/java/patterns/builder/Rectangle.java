package patterns.builder;

import java.util.Comparator;

public class Rectangle extends Shape
{

    private final double height;
    private final double width;

    public double getHeight()
    {
        return height;
    }

    public double getWidth()
    {
        return width;
    }

    public static abstract class Builder<T extends Rectangle> extends Shape.Builder<T>
    {
        private double height;
        private double width;

        public Builder<T> height(double height)
        {
            this.height = height;
            return this;
        }

        public Builder<T> width(double width)
        {
            this.width = width;
            return this;
        }
    }

    public static Builder<?> builder()
    {
        return new Builder<Rectangle>()
        {
            @Override
            public Rectangle build()
            {
                return new Rectangle(this);
            }
        };
    }

    protected Rectangle(Builder<?> builder)
    {
        super(builder);
        this.height = builder.height;
        this.width = builder.width;
    }

    public static double area(Rectangle rectangle)
    {
        return rectangle.height * rectangle.width;
    }

    public static Comparator<Rectangle> compareByHeight = Comparator.comparing(Rectangle::getHeight);
}
