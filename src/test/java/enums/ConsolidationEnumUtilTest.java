package enums;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.stream.Stream;

public class ConsolidationEnumUtilTest
{
    private static final Logger logger = LoggerFactory.getLogger(ConsolidationEnumUtilTest.class);

    @Test
    public void convertToEnum()
    {
        Assert.assertEquals(ConsolidationLockDetail.AttributeEntity.ORDER, ConsolidationEnumUtil.convertToEnum("ORDER", ConsolidationLockDetail.AttributeEntity.class));
        Assert.assertEquals(ConsolidationLockDetail.LpnLockBy.CN22_NUMBER, ConsolidationEnumUtil.convertToEnum("cn22Number", ConsolidationLockDetail.LpnLockBy.class));
        Assert.assertEquals(ConsolidationLockDetail.OrderLockBy.HOT_ORDER, ConsolidationEnumUtil.convertToEnum("hotOrder", ConsolidationLockDetail.OrderLockBy.class));
    }

    @Test
    public void isValid()
    {
        Assert.assertTrue(ConsolidationEnumUtil.isValid("LPN", ConsolidationLockDetail.AttributeEntity.class));
        Assert.assertFalse(ConsolidationEnumUtil.isValid("dummy", ConsolidationLockDetail.AttributeEntity.class));
        Assert.assertFalse(ConsolidationEnumUtil.isValid("dummy", ConsolidationLockDetail.LpnLockBy.class));
        Assert.assertFalse(ConsolidationEnumUtil.isValid("dummy", ConsolidationLockDetail.OrderLockBy.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertToAttributeEntityThrowsIllegalArgumentException()
    {
        ConsolidationEnumUtil.convertToEnum("dummy", ConsolidationLockDetail.AttributeEntity.class);
        Assert.fail("Should throw IllegalArgumentException");
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertToOrderLockByThrowsIllegalArgumentException()
    {
        ConsolidationEnumUtil.convertToEnum("dummy", ConsolidationLockDetail.OrderLockBy.class);
        Assert.fail("Should throw IllegalArgumentException");
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertToLpnLockByThrowsIllegalArgumentException()
    {
        ConsolidationEnumUtil.convertToEnum("dummy", ConsolidationLockDetail.LpnLockBy.class);
        Assert.fail("Should throw IllegalArgumentException");
    }

    @Test
    public void getCodes()
    {
        System.out.println("List of ConsolidationLockDetail.AttributeEntity: " + ConsolidationEnumUtil.getCodes(ConsolidationLockDetail.AttributeEntity.class));
        Stream.of(ConsolidationLockDetail.LpnLockBy.values()).forEach(e -> System.out.println("LpnLockBy elements: " + e.name() + "=" + e.toString()));
        Assert.assertArrayEquals(Stream.of("ORDER", "LPN").toArray(), ConsolidationEnumUtil.getCodes(ConsolidationLockDetail.AttributeEntity.class).toArray());
    }

    @Test
    public void getCodeValuePairs()
    {
        System.out.println("Fruits : " + ConsolidationEnumUtil.getCodeValuePairs(Fruits.class));
        Fruits[] fruits = Fruits.values();
        Arrays.asList(Fruits.values()).forEach(fruit -> logger.debug("Fruits {} rank {}", fruit.getDescription(), fruit.ordinal()));
    }
}