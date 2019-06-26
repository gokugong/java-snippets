package enums;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class StringToEnumConverterTest
{

    static StringToEnumConverter<ConsolidationLockDetail.AttributeEntity> toAttr = new StringToEnumConverter<>(ConsolidationLockDetail.AttributeEntity.class);
    static StringToEnumConverter<ConsolidationLockDetail.LpnLockBy> toLpn = new StringToEnumConverter<>(ConsolidationLockDetail.LpnLockBy.class);
    static StringToEnumConverter<ConsolidationLockDetail.OrderLockBy> toOrder = new StringToEnumConverter<>(ConsolidationLockDetail.OrderLockBy.class);

    @Test
    public void convert()
    {
        Assert.assertEquals(ConsolidationLockDetail.AttributeEntity.ORDER, toAttr.convert("ORDER"));
        Assert.assertEquals(ConsolidationLockDetail.LpnLockBy.CN22_NUMBER, toLpn.convert("cn22Number"));
        Assert.assertEquals(ConsolidationLockDetail.OrderLockBy.HOT_ORDER, toOrder.convert("hotOrder"));
    }
}