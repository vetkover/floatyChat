package me.vetkover.floatychat.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

import static me.vetkover.floatychat.stuff.YamlWork.readYaml;
import static me.vetkover.floatychat.stuff.perimssionWork.userHasPermission;


public class msg implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        String senderNickname = player.getName();
        String recipientNickname = strings[0];
        Player recipient = Bukkit.getPlayer(recipientNickname);

        if (userHasPermission(senderNickname, "floatychat.privatemessage") || (Boolean) readYaml("privateMessageByDefault")){
            if(recipient != null){

                String result = "";

                for (int i = 1; i < strings.length; i++) {
                    result += strings[i] + " ";
                }
                recipient.sendMessage("§9[Private] " + "§f" + senderNickname + "§7 => " + result);
                player.sendMessage("§9[Private] " + "§f" + "You" + "§7 => " + result);

            }
            else {
                player.sendMessage("we couldn't send him a message because he doesn't exist");
            }

        } else{
            player.sendMessage("§4access denied to this command");
        }

        return true;
    }
}
