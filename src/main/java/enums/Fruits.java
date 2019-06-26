package enums;

public enum Fruits implements UiEnumType
{
    APPLE("0", "Apple"), PEAR("1", "Pear");

    Fruits(String code, String description)
    {
        this.code = code;
        this.description = description;
    }

    private String code;
    private String description;


    @Override
    public String getCode()
    {
        return code;
    }

    @Override
    public String getValue()
    {
        return description;
    }

    public String getDescription()
    {
        return description;
    }

    @Override
    public String toString()
    {
        return code;
    }
}
