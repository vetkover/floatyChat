package me.vetkover.floatychat;

import me.vetkover.floatychat.events.Events;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import static me.vetkover.floatychat.stuff.checkingFiles.checkDirectory;
import static me.vetkover.floatychat.stuff.checkingFiles.checkFiles;

public final class FloatyChat extends JavaPlugin {

    @Override
    public void onEnable() {
        checkDirectory();
        checkFiles();
        Bukkit.getPluginManager().registerEvents(new Events(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
