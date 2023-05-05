package me.vetkover.floatychat.events;

import me.vetkover.floatychat.stuff.YamlWork;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static me.vetkover.floatychat.stuff.JsonWork.findOneJson;
import static me.vetkover.floatychat.stuff.YamlWork.readYaml;
import static me.vetkover.floatychat.stuff.perimssionWork.userHasPermission;

public class onPlayerDeath implements Listener {
    public onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        Player victim = event.getEntity();
        Player killer = event.getEntity().getKiller();
        String victimNick = event.getEntity().getName();
        String killerNick = killer.getName();

        if((boolean) readYaml("enableCustomDeathMessage") && killer != null){
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                YamlWork.formatingYaml(player, readYaml("customDeathMessage"));
            }
        }
    }
}
