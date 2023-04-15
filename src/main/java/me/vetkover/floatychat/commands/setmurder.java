package me.vetkover.floatychat.commands;

import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import static me.vetkover.floatychat.stuff.JsonWork.writeJson;
import static me.vetkover.floatychat.stuff.YamlWork.readYaml;

public class setmurder implements CommandExecutor {
    long currentTimeInSeconds = System.currentTimeMillis() / 1000;
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Server server = Bukkit.getServer();
        Player player = (Player) commandSender;

        User playerLuck = LuckPermsProvider.get().getUserManager().getUser(commandSender.getName());
        if (player.hasPermission("ismurderer.setmurder") || playerLuck.getCachedData().getPermissionData().checkPermission("ismurderer.setmurder").asBoolean()) {


            String Killerplayer = strings[0];
            Player targetPlayer = server.getPlayer(Killerplayer);
            String targertPlayerNick = targetPlayer.getName();

            ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
            Scoreboard scoreboard = scoreboardManager.getMainScoreboard();
            Team team1 = scoreboard.getTeam("sus");
            Team team2 = scoreboard.getTeam("murder");


            if (strings[1].equals("sus")) {

                if (targetPlayer != null) {
                    writeJson(targertPlayerNick, (int) (currentTimeInSeconds + ((Number) readYaml("punishSusDuration")).intValue()), "SUS");
                    targetPlayer.setDisplayName("[SUS] " + targertPlayerNick);
                    targetPlayer.setPlayerListName("[SUS] " + targertPlayerNick);

                    team1.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.ALWAYS);
                    team1.addEntry(targertPlayerNick);
                    player.setScoreboard(scoreboard);
                } else {
                    commandSender.sendMessage("Player was not found");
                }
            }
            if (strings[1].equals("murder")) {

                if (targetPlayer != null) {
                    writeJson(targertPlayerNick, (int) (currentTimeInSeconds + ((Number) readYaml("punishMurderDuration")).intValue()), "MURDER");
                    targetPlayer.setDisplayName("[MURDER] " + targertPlayerNick);
                    targetPlayer.setPlayerListName("[MURDER] " + targertPlayerNick);

                    team2.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.ALWAYS);
                    team2.addEntry(targertPlayerNick);
                    player.setScoreboard(scoreboard);
                } else {
                    commandSender.sendMessage("Player was not found");
                }
            }
        } else {
            commandSender.sendMessage("access denied");
        }
        return true;
    }
}

