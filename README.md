# EazyExceptionsHandler

Experimental and useless exceptions handler for Bukkit plugins and normal Java applications 

```Java
public final class Main extends JavaPlugin implements EazyExceptionsListener
{
    @Override
    public void onEnable()
    {
        BukkitEzExceptionsHandler.getInstance(this).registerExceptionListener(this);
        test();
    }

    @ExceptionGoesBrr(exception = ArrayIndexOutOfBoundsException.class, 
                      message = "Very cool, ur a skid and I smell an exception", 
                      printStackTrace = false)
    private void test()
    {
        int[] test = new int[0];
        test[69] = 1337;
    }
}
```

![](https://i.imgur.com/X8YWuIQ.png)
