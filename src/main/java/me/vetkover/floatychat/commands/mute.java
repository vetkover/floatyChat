package me.vetkover.floatychat.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


import static me.vetkover.floatychat.stuff.JsonWork.editOneJson;
import static me.vetkover.floatychat.stuff.JsonWork.findOneJson;
import static me.vetkover.floatychat.stuff.YamlWork.readYaml;
import static me.vetkover.floatychat.stuff.perimssionWork.userHasPermission;


public class mute implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        String senderNickname = player.getName();
        String recipientNickname = strings[0];
        Player recipient = Bukkit.getPlayer(recipientNickname);
        Integer commandMuteTime = 0;

        if (strings.length > 1) {
            commandMuteTime = Integer.valueOf(strings[1]);
        }

        long currentTimeInSeconds = System.currentTimeMillis() / 1000;
        Long punishTime = currentTimeInSeconds + (Integer) readYaml("mutePunish");

        if (userHasPermission(senderNickname, "floatychat.mute") || userHasPermission(senderNickname, "floatychat.*")) {
                if (commandMuteTime != 0) {
                    editOneJson(recipientNickname, "mute", punishTime);

                    int hours = commandMuteTime / 3600;
                    int minutes = (commandMuteTime % 3600) / 60;
                    int seconds = commandMuteTime % 60;


                    player.sendMessage("you muted " + recipientNickname + " on " + String.format("%02d:%02d:%02d", hours, minutes, seconds));
                    recipient.sendMessage("you was muted on " + String.format("%02d:%02d:%02d", hours, minutes, seconds));
                } else {
                    int muteTime = (int) readYaml("mutePunish");
                    editOneJson(recipientNickname, "mute", currentTimeInSeconds + muteTime);
                    int hours = muteTime / 3600;
                    int minutes = (muteTime % 3600) / 60;
                    int seconds = muteTime % 60;

                    player.sendMessage("you muted " + recipientNickname + " on " + String.format("%02d:%02d:%02d", hours, minutes, seconds));
                    recipient.sendMessage("you was muted on " + String.format("%02d:%02d:%02d", hours, minutes, seconds));
                }
            } else {
                player.sendMessage("§4access denied to this command");
            }
        return true;
    }
}
