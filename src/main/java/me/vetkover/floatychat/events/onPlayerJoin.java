package me.vetkover.floatychat.events;

import me.vetkover.floatychat.stuff.JsonWork;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;
import org.json.JSONObject;

import static me.vetkover.floatychat.stuff.YamlWork.readYaml;

public class onPlayerJoin implements Listener {
    public onPlayerJoin(PlayerJoinEvent event) {
        String nickname = event.getPlayer().getName();
        Player player = Bukkit.getPlayer(nickname);
        //JSONObject userJSON = JsonWork.findOneJson(nickname);

        player.sendMessage((String) readYaml("greetingMessage"));

    }
}
