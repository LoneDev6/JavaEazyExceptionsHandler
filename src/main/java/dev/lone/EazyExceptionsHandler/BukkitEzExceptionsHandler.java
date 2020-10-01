package dev.lone.EazyExceptionsHandler;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;

/**
 * Wrapper of {@link EazyExceptionsHandler} for Bukkit plugins.
 *
 * This is an experimental not useful concept, I was bored.
 */
public class BukkitEzExceptionsHandler extends EazyExceptionsHandler implements Listener
{
    private static BukkitEzExceptionsHandler instance;

    private org.bukkit.plugin.Plugin plugin;

    public BukkitEzExceptionsHandler(org.bukkit.plugin.Plugin plugin)
    {
        super(Bukkit.getLogger());
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public static BukkitEzExceptionsHandler getInstance(org.bukkit.plugin.Plugin plugin)
    {
        if(instance == null)
            instance = new BukkitEzExceptionsHandler(plugin);
        return instance;
    }

    @EventHandler
    private void pluginDisable(PluginDisableEvent e)
    {
        if(!e.getPlugin().getName().equals(plugin.getName()))
            return;
        detach();
    }
}
