package me.vetkover.floatychat.stuff;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.yaml.snakeyaml.Yaml;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class YamlWork {

    public static void createYaml(File configFile) {
        try {
            FileWriter writer = new FileWriter(configFile);
            writer.write("#if something is broken just delete the file :3'\n");

            writer.write("enableGreeting: true \n");
            writer.write("enableFirstGreeting: true \n");
            writer.write("enableCustomDeathMessage: true \n");
            writer.write("enableTimerMessages: false \n");

            writer.write("localChatByDefault: true\n");
            writer.write("globalChatByDefault: true\n");
            writer.write("privateMessageByDefault: true\n");

            writer.write("localChatRange: 40\n");
            writer.write("mutePunish: 3600\n");

            writer.write("globalChatSymbol: !\n");
            writer.write("globalChatPrefix: Global\n");
            writer.write("localChatPrefix: Local\n");

            writer.write("greetingMessage: welcome back §6{nickname1}§f, now on server {time:?format=h:mm a}. See ya on our site {URL:?text=click?url=https://sitexample.com}!\n");
            writer.write("firstGreetingMessage: welcome {nickname1}, now on server {time}. Check our site {URL:?text=click?url=https://sitexample.com}\n");
            writer.write("customDeathMessage: {nickname1} killed a {nickname2}\n");

            writer.write("TimerMessages:\n");
            writer.write(" - example string 1\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static String appDir = System.getProperty("user.dir");



    public static Object readYaml(String dataGet) {

        Yaml yaml = new Yaml();
        try {
            InputStream in = Files.newInputStream(Paths.get(appDir + "/plugins/floatyChat/config.yaml"));
            Map<String, Object> data = yaml.load(in);
            Object value = data.get(dataGet);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//
public static String[] readYamlAdverts() {
    try {

        InputStream input = Files.newInputStream(Paths.get(appDir + "/plugins/floatyChat/config.yaml"));
        Yaml yaml = new Yaml();

        Map<String, Object> data = yaml.load(input);
        String targetKey = "TimerMessages";

        List<String> content = findContentByKey(data, targetKey);
        String[] contentArray = content.toArray(new String[0]);
        return contentArray;
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
    return null;
}


    private static List<String> findContentByKey(Map<String, Object> data, String targetKey) {
        List<String> content = new ArrayList<>();

        for (Map.Entry<String, Object> entry : data.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (key.equals(targetKey)) {
                if (value instanceof List) {
                    processNestedList((List<?>) value, content);
                } else {
                }
                break;
            }
        }

        return content;
    }

    private static void processNestedList(List<?> list, List<String> content) {
        for (Object item : list) {
            if (item instanceof String) {
                content.add((String) item);
            }
        }
    }

//
    public static void formatingYaml(Player player1, Object stringYaml, Boolean toAll) {
        String Yaml = stringYaml.toString();
        String nickname1 = player1.getPlayer().getName();
        String killerName = (player1.getKiller() != null) ? player1.getKiller().getName() : "power of nature";
        Date now = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
        String time = sdf.format(now);


        String YAMLMessage = Yaml
                .replaceAll("\\{nickname2\\}", killerName)
                .replaceAll("\\{time\\}", time)
                .replaceAll("\\{time:\\?format=([^}]+).", "{TIMEWARP}")
                .replaceAll("\\{URL:\\?[^}]*text=([^}|?]+)\\?*url=([^}|?]+)}", "{URLWARP}")
                .replaceAll("\\{nickname1\\}", nickname1);

        if(YAMLMessage.contains("{TIMEWARP}") || YAMLMessage.contains("{URLWARP}")) {
            if (YAMLMessage.contains("{TIMEWARP}")) {
                Pattern pattern = Pattern.compile("\\{time:\\?format=([^}]+).");

                Matcher matcher = pattern.matcher(Yaml);
                Boolean urlPaternInString = matcher.find();

                String TIMEformat = matcher.group(1);
                SimpleDateFormat timeFormat = new SimpleDateFormat(TIMEformat);
                String Newtime = timeFormat.format(now);
                YAMLMessage = YAMLMessage.replaceAll("\\{TIMEWARP\\}", Newtime);
                Yaml = Yaml.replaceAll("\\{time:\\?format=([^}]+).", Newtime);

            }

            if (YAMLMessage.contains("{URLWARP}")) {
                Pattern pattern = Pattern.compile("\\{URL:\\?[^}]*text=([^}|?]+)\\?*url=([^}|?]+)}");

                Matcher matcher = pattern.matcher(Yaml);
                Boolean urlPaternInString = matcher.find();

                String URLtext = matcher.group(1);
                String URLurl = matcher.group(2);

                String[] parts = YAMLMessage.toString().split("\\{URLWARP\\}", 2);

                TextComponent link = new TextComponent(URLtext);
                link.setColor(ChatColor.GREEN);
                link.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, URLurl));

                BaseComponent[] message = TextComponent.fromLegacyText(parts[0]);
                message[message.length - 1].addExtra(link);
                BaseComponent[] rest = TextComponent.fromLegacyText(parts[1]);
                BaseComponent[] finalMessage = new BaseComponent[message.length + rest.length];
                System.arraycopy(message, 0, finalMessage, 0, message.length);
                System.arraycopy(rest, 0, finalMessage, message.length, rest.length);

                player1.spigot().sendMessage(finalMessage);
            }
        } else {
           if(toAll) {
               for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                   onlinePlayer.sendMessage(YAMLMessage);
               }
           } else {
               player1.sendMessage(YAMLMessage);
           }
        }
    }
}
