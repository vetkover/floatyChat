package me.vetkover.floatychat.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class Events implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event)
    {
        new onPlayerChat(event);
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        new onPlayerJoin(event);
    }
}
