package dev.lone.EazyExceptionsHandler;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * This is an experimental not useful concept, I was bored.
 */
public class EazyExceptionsHandler extends java.util.logging.Handler
{
    private HashMap<String, ExceptionListenerData> exceptionListenerData = new HashMap<>();
    private Logger logger;

    public EazyExceptionsHandler(Logger logger)
    {
        this.logger = logger;
        attach(logger);
    }

    private void attach(Logger logger)
    {
        logger.addHandler(this);
    }

    /**
     * Detach this {@link java.util.logging.Handler} from {@link Logger}
     */
    public void detach()
    {
        logger.removeHandler(this);
    }

    /**
     * Remember to register your classes that implement {@link EazyExceptionsListener} and which have {@link ExceptionGoesBrr}
     * annotation on any method.
     * @param listener Your class which implements {@link EazyExceptionsListener}
     */
    public void registerExceptionListener(EazyExceptionsListener listener)
    {
        exceptionListenerData.put(
                listener.getClass().getCanonicalName(),
                new ExceptionListenerData(listener)
        );
    }

    private ExceptionGoesBrr getAnnotationByLogRecord(LogRecord logRecord)
    {
        for(StackTraceElement caller : logRecord.getThrown().getStackTrace())
        {
            ExceptionListenerData entry = exceptionListenerData.get(caller.getClassName());
            if (entry == null)
                continue;

            ExceptionGoesBrr annotation = entry.getAnnotations().get(caller.getMethodName());
            if (annotation == null)
                continue;

            if (annotation.exception() != logRecord.getThrown().getClass())
                continue;

            return annotation;
        }
        return null;
    }

    @Override
    public void publish(LogRecord logRecord)
    {
        if(logRecord.getLevel() != Level.SEVERE && logRecord.getLevel() != Level.WARNING)
            return;
        if(logRecord.getThrown() == null)
            return;

        ExceptionGoesBrr annotation = getAnnotationByLogRecord(logRecord);
        if(annotation == null)
            return;

        logger.log(logRecord.getLevel(), annotation.message());

        if(!annotation.printStackTrace())
            logRecord.setLevel(Level.OFF);
    }

    @Override
    public void close() throws SecurityException {}

    @Override
    public void flush() {}
}