package dev.lone.EazyExceptionsHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExceptionGoesBrr
{
    Class<? extends Exception> exception();
    String message();
    boolean printStackTrace();
}
