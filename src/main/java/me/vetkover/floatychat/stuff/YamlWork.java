package me.vetkover.floatychat.stuff;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.yaml.snakeyaml.Yaml;

public class YamlWork {

    public static void createYaml(File configFile) {
        try {
            FileWriter writer = new FileWriter(configFile);

            writer.write("#if something is broken just delete the file :3'\n");
            writer.write("enableGreeting: true \n");
            writer.write("welcome back {nickname}, now on server {time}.\\nSee you on our site {URL:?text=click?url=https://examplesite.com}!\n");
            writer.write("localChatRange: 40\n");
            writer.write("localChatByDefault: true\n");
            writer.write("globalChatByDefault: true\n");
            writer.write("globalChatSymbol: !\n");
            writer.write("globalChatPrefix: Global\n");
            writer.write("localChatPrefix: Local\n");
            writer.write("privateMessageByDefault: true\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static String appDir = System.getProperty("user.dir");

    public static Object readYaml(String dataGet) {

        Yaml yaml = new Yaml();
        try {
            InputStream in = Files.newInputStream(Paths.get(appDir + "/plugins/FloatyChat/config.yaml"));
            Map<String, Object> data = yaml.load(in);
            Object value = data.get(dataGet);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void formatingYaml(Player player1, Object stringYaml) {
        String nickname = player1.getPlayer().getName();
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
        String time = sdf.format(now);

        Pattern pattern = Pattern.compile("\\{URL:\\?[^}]*text=([^}|?]+)\\?*url=([^}|?]+)}");

        Matcher matcher = pattern.matcher(stringYaml.toString());
        Boolean urlPaternInString = matcher.find();

        String URLtext = matcher.group(1);
        String URLurl = matcher.group(2);

        if (urlPaternInString) {
            URLtext = matcher.group(1);
            URLurl = matcher.group(2);
        }

        Object greetingMessage = stringYaml.toString()
                .replaceAll("\\{nickname\\}", nickname)
                .replaceAll("\\{time\\}", time)
                .replaceAll("\\{URL:\\?[^}]*text=([^}|?]+)\\?*url=([^}|?]+)}", "{URLWARP}");

        if (greetingMessage.toString().contains("{URLWARP}")) {
            String[] parts = greetingMessage.toString().split("\\{URLWARP\\}", 2);

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
        } else {
            player1.sendMessage(greetingMessage.toString().trim());
        }


    }
}
