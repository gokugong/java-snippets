package patterns.builderinterface;

// https://chrononaut.org/2012/02/24/subclassing-with-blochs-builder-pattern/comment-page-1/#comment-7637
//

public class Shape
{
    private final double opacity;

    public double getOpacity()
    {
        return opacity;
    }

    public static abstract class Builder<T extends Shape> {

        public double getOpacity()
        {
            return opacity;
        }

        private double opacity;

        public Builder<T> opacity(double opacity) {
            this.opacity = opacity;
            return this;
        }

        public abstract T build();
    }

    public static Builder<? extends Shape> builder() {
        return new Builder<Shape>()
        {
            @Override
            public Shape build()
            {
                return new Shape(this);
            }
        };
    }

    protected Shape(Builder<? extends Shape> builder) {
        this.opacity = builder.opacity;
    }
}
