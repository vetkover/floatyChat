package me.vetkover.floatychat.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static me.vetkover.floatychat.stuff.JsonWork.findOneJson;
import static me.vetkover.floatychat.stuff.YamlWork.readYaml;
import static me.vetkover.floatychat.stuff.perimssionWork.userHasPermission;

public class onPlayerChat implements Listener {
    public onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        event.setCancelled(true);
        String message = event.getMessage();
        String author = player.getDisplayName();
        String realName = player.getName();
        long currentTimeInSeconds = System.currentTimeMillis() / 1000;
        int localChatRange = ((Integer) readYaml("localChatRange")).intValue();

        if (findOneJson(realName).getInt("mute") < currentTimeInSeconds) {

            if (message.charAt(0) == '!') {
                if ((userHasPermission(realName, "floatychat.globalchat") || (Boolean) readYaml("globalChatByDefault"))) {
                    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                        onlinePlayer.sendMessage("§e[" + readYaml("globalChatPrefix") + "] §f" + author + " §8» §e" + message.substring(1));
                    }
                } else {
                player.sendMessage("You do not have permission to use global chat.");
                event.setCancelled(true);
                }
            }

            if (message.charAt(0) != '!'){
                if ((userHasPermission(realName, "floatychat.localchat")) || (Boolean) readYaml("localChatByDefault")) {
                    for (Player other : Bukkit.getOnlinePlayers()) {
                        if (other.getLocation().distance(player.getLocation()) <= localChatRange) {
                            other.sendMessage("§e[" + readYaml("localChatPrefix") + "] §f" + author + " §8» §e" + message);
                        }
                    }
                } else {
                    player.sendMessage("You do not have permission to use local chat.");
                    event.setCancelled(true);
                }
            }

        } else {
            player.sendMessage("You can't write while you're muted.");
            event.setCancelled(true);
        }
        event.setCancelled(true);
    }
}
