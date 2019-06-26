package enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A set of generics utils for Enum. Need override toString to use
 *
 * <p>Usage example:</p>
 * <p>
 * <code>AttributeEntity ae = convertToEnum("ORDER", AttributeEnity.class)</code>
 * will return <code>AttributeEntity.ORDER</code>
 *
 * </p>
 */
public interface ConsolidationEnumUtil
{

    /**
     * Convert code to the matching Enum element
     *
     * @param code
     * @param clazz
     * @param <E>
     * @return
     * @throws IllegalArgumentException
     */
    static <E extends Enum<E>> E convertToEnum(String code, Class<E> clazz) throws IllegalArgumentException
    {
        List<E> elements = EnumSet.allOf(clazz).stream().collect(Collectors.toList());
        for (E e : elements)
        {
            if (e.toString().equals(code))
                return e;
        }
        throw new IllegalArgumentException("Not an Enum code of " + clazz);
    }

    /**
     * Return true if is valid Enum code
     *
     * @param code
     * @param clazz
     * @param <E>
     * @return
     */
    static <E extends Enum<E>> boolean isValid(String code, Class<E> clazz)
    {
        if (code == null)
            return false;
        List<E> elements = EnumSet.allOf(clazz).stream().collect(Collectors.toList());
        for (E e : elements)
        {
            if (e.toString().equals(code))
                return true;
        }
        return false;
    }

    /**
     * Return list of Enum code values
     *
     * @param clazz
     * @param <E>
     * @return
     */
    static <E extends Enum<E>> List<String> getCodes(Class<E> clazz)
    {
        return EnumSet.allOf(clazz).stream().map(e -> e.toString()).collect(Collectors.toList());
    }

    static <E extends Enum<E> & UiEnumType> Map<String, String> getCodeValuePairs(Class<E> clazz)
    {
        EnumSet.allOf(clazz).forEach(e -> System.out.println("(" + e.getCode() + ", " + e.getValue() + ")"));
        Map<String, String> codeValuePairs = new HashMap<>();
        EnumSet.allOf(clazz).forEach(e -> codeValuePairs.put(e.getCode(), e.getValue()));
        return codeValuePairs;
    }

}
