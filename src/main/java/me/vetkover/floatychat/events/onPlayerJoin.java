package me.vetkover.floatychat.events;

import me.vetkover.floatychat.stuff.YamlWork;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static me.vetkover.floatychat.stuff.YamlWork.readYaml;

public class onPlayerJoin implements Listener {
    public onPlayerJoin(PlayerJoinEvent event) {
        String nickname = event.getPlayer().getName();
        Player player = Bukkit.getPlayer(nickname);
        //JSONObject userJSON = JsonWork.findOneJson(nickname);

        YamlWork.formatingYaml(player, readYaml("greetingMessage"));


    }
}
