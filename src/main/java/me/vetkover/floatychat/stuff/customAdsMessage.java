package me.vetkover.floatychat.stuff;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import static me.vetkover.floatychat.stuff.YamlWork.readYamlAdverts;

public class customAdsMessage {

    public static void customAdsMessageStartUp() {
        String[] messages = readYamlAdverts();
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
        Runnable task1 = () -> {
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                onlinePlayer.sendMessage(messages[0]);
            }
        };
        // init Delay = 5, repeat the task every 1 second
        ScheduledFuture<?> scheduledFuture = ses.scheduleAtFixedRate(task1, 5, 10, TimeUnit.SECONDS);
    }
}


