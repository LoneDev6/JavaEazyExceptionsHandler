package dev.lone.EazyExceptionsHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * <h1>Do not use!</h1>
 * internal use only.
 */
public class ExceptionListenerData
{
    private HashMap<String, ExceptionGoesBrr> annotations;

    public ExceptionListenerData(EazyExceptionsListener listener)
    {
        this.annotations = findAnnotationInClassMethods(listener.getClass(), ExceptionGoesBrr.class);
    }

    public HashMap<String, ExceptionGoesBrr> getAnnotations()
    {
        return annotations;
    }

    private static <T extends Annotation> HashMap<String, T> findAnnotationInClassMethods(final Class<?> type, final Class<? extends Annotation> annotation)
    {
        final HashMap<String, T> methods = new HashMap<>();
        Class<?> klass = type;
        while (klass != Object.class)
        {
            // need to iterated thought hierarchy in order to retrieve methods from above the current instance
            // iterate though the list of methods declared in the class represented by klass variable, and add those annotated with the specified annotation
            for (final Method method : klass.getDeclaredMethods())
            {
                if (method.isAnnotationPresent(annotation))
                {
                    Annotation annotInstance = method.getAnnotation(annotation);
                    methods.put(method.getName(), (T) annotInstance);
                }
            }
            // move to the upper class in the hierarchy in search for more methods
            klass = klass.getSuperclass();
        }
        return methods;
    }
}
