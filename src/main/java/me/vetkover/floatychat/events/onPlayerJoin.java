package me.vetkover.floatychat.events;

import me.vetkover.floatychat.stuff.JsonWork;
import me.vetkover.floatychat.stuff.YamlWork;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.json.JSONException;
import org.json.JSONObject;

import static me.vetkover.floatychat.stuff.JsonWork.writeJson;
import static me.vetkover.floatychat.stuff.YamlWork.readYaml;

public class onPlayerJoin implements Listener {
    public onPlayerJoin(PlayerJoinEvent event) {
        String nickname = event.getPlayer().getName();
        Player player1 = event.getPlayer();
        JSONObject userJSON = null;
        try{
            userJSON = JsonWork.findOneJson(nickname);
            if(userJSON == null){
                writeJson(nickname, 0, false);
                userJSON = JsonWork.findOneJson(nickname);
            }
        } catch (JSONException e ){}

        if(userJSON != null && !userJSON.getBoolean("notFirstEnter") && (boolean) readYaml("enableFirstGreeting")){
            JsonWork.editOneJson(nickname, "notFirstEnter", true);
            YamlWork.formatingYaml(player1, readYaml("firstGreetingMessage"));
        } else if (userJSON != null && (boolean) readYaml("enableGreeting")){
            YamlWork.formatingYaml(player1, readYaml("greetingMessage"));
        }
        event.setJoinMessage(null);
    }

}
