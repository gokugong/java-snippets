package misc;

import java.util.function.Function;

/**
 Wrapper (Decorator) design pattern.
 The core idea here is transforming text: text in, text out.
 */
public final class WrapperDemoFunctional {

    /** Demo harness. */
    public static void main(String... args){
        //we take a simple trim to be the core operation here:
        Function<String, String> coreOperation = p -> p.trim();
        String output = coreOperation.apply(" blah ");
        show(output); // 'blah'

        //now we can add behaviour to the core operation, by wrapping it
        //in other objects that implement the same (functional) interface
        Function<String, String> wrappedOperation = new Capitalizer(coreOperation);
        //notice it does TWO things: trims (core) and capitalizes (wrapper)
        show(wrappedOperation.apply(" blah ")); // 'BLAH'

        int MAX_CHARS = 3;
        wrappedOperation = new Chopper(coreOperation, MAX_CHARS);
        show(wrappedOperation.apply(" blah ")); // 'bla'

        //you can chain objects together with wild abandon, to implement
        //many glorious variations on behaviour
        wrappedOperation = new Chopper(new Capitalizer(coreOperation), MAX_CHARS);
        show(wrappedOperation.apply(" blah ")); // 'BLA'
    }

    /** Show the result of the text transformations. */
    private static void show(String text){
        String QT = "'";
        System.out.println(QT + text + QT);
    }

    /**
     This class both implements the interface AND is constructed
     with an implementation of the same (functional) interface.
     */
    private static class BaseWrapper implements Function<String, String> {
        BaseWrapper(Function<String, String> transformer){
            this.transformer = transformer;
        }
        /** Template method, calls 'before' and 'after' methods. */
        @Override public String apply(String aText) {
            String text = before(aText);
            text = transformer.apply(text); //call-forward
            return after(text);
        }
        /** This overridable default implementation does nothing.*/
        String before(String text){
            return text;
        }
        /** This overridable default implementation does nothing.*/
        String after(String text){
            return text;
        }
        private Function<String, String> transformer;
    }

    /** Capitalize before applying the core operation. */
    private static final class Capitalizer extends BaseWrapper {
        Capitalizer(Function<String, String> transformer){
            super(transformer);
        }
        @Override String before(String text) {
            String result = text;
            if (text != null){
                result = result.toUpperCase();
            }
            return result;
        }
    }

    /** Limit to N characters after applying the core operation. */
    private static final class Chopper extends BaseWrapper {
        Chopper(Function<String, String> transformer, int limit){
            super(transformer);
            this.limit = limit;
        }
        @Override String after(String text) {
            String result = text;
            if (text != null){
                result = result.substring(0, limit);
            }
            return result;
        }
        private int limit;
    }
}