package patterns.builderinterface;

public class RotatedRectangle extends Rectangle
{
    private final double theta;

    public double getTheta()
    {
        return theta;
    }

    public static abstract class Builder<T extends RotatedRectangle> extends Rectangle.Builder<T> {
        private double theta;

        public Builder<T> theta(double theta) {
            this.theta = theta;
            return this;
        }
    }

    public static Builder<?> builder() {
        return new Builder<RotatedRectangle>()
        {
            @Override
            public RotatedRectangle build()
            {
                return new RotatedRectangle(this);
            }
        };
    }

    protected RotatedRectangle(Builder<?> builder) {
        super(builder);
        this.theta = builder.theta;
    }
}