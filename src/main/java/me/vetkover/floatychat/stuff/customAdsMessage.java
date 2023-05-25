package me.vetkover.floatychat.stuff;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static me.vetkover.floatychat.stuff.YamlWork.readYaml;
import static me.vetkover.floatychat.stuff.YamlWork.readYamlAdverts;

public class customAdsMessage {
    private static AtomicInteger messageIndex = new AtomicInteger(0);

    public static void customAdsMessageStartUp() {
        String[] messages = readYamlAdverts();
        int messagesAmount = messages.length;
        if (readYamlAdverts() != null && ((boolean) readYaml("enableTimerMessages"))) {
            ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
            if (messageIndex.get() < messagesAmount) {
                Runnable task1 = () -> {
                    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                        onlinePlayer.sendMessage(messages[messageIndex.get()]);
                    }
                    messageIndex.incrementAndGet();
                    if (messageIndex.get() >= messagesAmount) {
                        messageIndex.set(0);
                    }
                };
                ScheduledFuture<?> scheduledFuture = ses.scheduleAtFixedRate(task1, 5, ( (Integer) readYaml("intervalTimerMessages")), TimeUnit.SECONDS);
            }
        }
    }
}
