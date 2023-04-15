package me.vetkover.floatychat.commands;

import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import static me.vetkover.floatychat.stuff.JsonWork.deleteOneJson;

public class clearmurder implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;

        User playerLuck = LuckPermsProvider.get().getUserManager().getUser(commandSender.getName());
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = scoreboardManager.getMainScoreboard();
        Team team1 = scoreboard.getTeam("sus");
        Team team2 = scoreboard.getTeam("murder");
        if (playerLuck.getCachedData().getPermissionData().checkPermission("ismurderer.clearmurder").asBoolean()) {

            String focusPlayer = strings[0];

            Player playerArgs = Bukkit.getPlayer(focusPlayer);
            boolean playerWasFoundAtList = deleteOneJson(focusPlayer);
            if (playerArgs != null && playerWasFoundAtList) {
                String newName;
                if (playerArgs.getDisplayName().contains("[SUS]")) {
                    newName = playerArgs.getDisplayName().replace("[SUS] ", "");
                    playerArgs.setDisplayName(newName);
                    playerArgs.setPlayerListName(newName);
                    team1.removeEntry(focusPlayer);
                    commandSender.sendMessage(newName + " lost his status [SUS]");
                }
                if (playerArgs.getDisplayName().contains("[MURDER]")) {
                    newName = playerArgs.getDisplayName().replace("[MURDER] ", "");
                    playerArgs.setDisplayName(newName);
                    playerArgs.setPlayerListName(newName);
                    team2.removeEntry(focusPlayer);
                    commandSender.sendMessage(newName + " lost his status [MURDER]");
                }
            }
        } else {
            commandSender.sendMessage("access denied");
        }
        return true;
    }
}
