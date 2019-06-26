package mvel;

import org.apache.commons.lang3.BooleanUtils;
import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.compiler.CompiledExpression;
import org.mvel2.compiler.ExpressionCompiler;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class MvelDemo
{
    private static final String expr = "$a > 0 && $b > 0 && $map.containsKey($key)";

    public static Function<Object, Boolean> mvelEx = ctx -> {
        Serializable expression = MVEL.compileExpression(expr, ParserContext.create());
        return (Boolean) MVEL.executeExpression(expression, ctx);
    };

    public static void main(String[] args)
    {
        Map<String, String> pets = new HashMap<>();
        pets.put("dog", "DOG");
        pets.put("cat", "CAT");
        Integer a = 10;
        Integer b = 20;

        Map<String, Object> params = new HashMap<>();
        params.put("$a", a);
        params.put("$b", b);
        params.put("$map", pets);
        params.put("$key", "dog");

        CompiledExpression expression = new ExpressionCompiler("$a > 0 && $b > 0 && $map.containsKey($key)").compile();
        System.out.println(MVEL.executeExpression(expression, params));
        System.out.println(MVEL.eval("$a > 0 && $b > 0 && $map.containsKey($key)", params));
        System.out.println(mvelEx.apply(params));

        Boolean testBool = null;
        boolean isBool = BooleanUtils.toBoolean(testBool);
        System.out.println("isBool = " + isBool);
    }

}
