package dev.lone.EazyExceptionsHandler;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements EazyExceptionsListener
{
    @Override
    public void onEnable()
    {
        BukkitEzExceptionsHandler.getInstance(this).registerExceptionListener(this);
        test();
    }

    @ExceptionGoesBrr(exception = ArrayIndexOutOfBoundsException.class, message = "Very cool, ur a skid and I smell an exception", printStackTrace = false)
    private void test()
    {
        int[] test = new int[0];
        test[69] = 1337;
    }
}
