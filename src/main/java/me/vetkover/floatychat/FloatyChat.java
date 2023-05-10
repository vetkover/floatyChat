package me.vetkover.floatychat;

import me.vetkover.floatychat.commands.msg;
import me.vetkover.floatychat.commands.mute;
import me.vetkover.floatychat.events.Events;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import static me.vetkover.floatychat.stuff.checkingFiles.checkDirectory;
import static me.vetkover.floatychat.stuff.checkingFiles.checkFiles;

public final class FloatyChat extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new Events(), this);
        checkDirectory();
        checkFiles();
        Bukkit.getPluginManager().registerEvents(new Events(), this);
        getCommand("msg").setExecutor(new msg());
        getCommand("mute").setExecutor(new mute());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
