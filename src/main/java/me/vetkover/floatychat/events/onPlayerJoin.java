package me.vetkover.floatychat.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static me.vetkover.floatychat.stuff.YamlWork.formatingYaml;
import static me.vetkover.floatychat.stuff.YamlWork.readYaml;

public class onPlayerJoin implements Listener {
    public onPlayerJoin(PlayerJoinEvent event) {
        String nickname = event.getPlayer().getName();
        Player player = Bukkit.getPlayer(nickname);
        //JSONObject userJSON = JsonWork.findOneJson(nickname);

        String str = formatingYaml(player, readYaml("greetingMessage"));
        int time = 15;

        String greetingMessage = str
                .replaceAll("\\{nickname\\}", nickname)
                .replaceAll("\\{time\\}", String.valueOf(time));
        player.sendMessage(greetingMessage);

    }
}
