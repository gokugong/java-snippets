package enums;

import org.springframework.core.convert.converter.Converter;

import java.util.EnumSet;

class StringToEnumConverter<T extends Enum> implements Converter<String, T>
{
    private Class<T> enumType;

    public StringToEnumConverter(Class<T> enumType)
    {
        this.enumType = enumType;
    }

    public T convert(String source)
    {
        return (T) EnumSet.allOf(enumType).stream().filter(e -> e.toString().equals(source)).findFirst().orElse(null);
    }
}