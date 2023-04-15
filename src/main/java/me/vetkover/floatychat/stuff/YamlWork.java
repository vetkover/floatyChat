package me.vetkover.floatychat.stuff;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class YamlWork {

    public static void createYaml(File configFile) {
        try {
        FileWriter writer = new FileWriter(configFile);

        writer.write("#if something is broken just delete the file :3'\n");
        writer.write("enableGreeting: true \n");
        writer.write("greetingMessage: hello ${nickname}\n");
        writer.write("localChatRange: 40\n");
        writer.write("localChatByDefault: true\n");
        writer.write("globalChatByDefault: true\n");
        writer.write("globalChatSymbol: !\n");
        writer.write("globalChatPrefix: Global\n");
        writer.write("localChatPrefix: Local\n");
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
    }



