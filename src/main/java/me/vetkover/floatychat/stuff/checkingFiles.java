package me.vetkover.floatychat.stuff;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class checkingFiles {
    static String appDir = System.getProperty("user.dir");
        public static void checkDirectory() {
            String directoryPath = appDir + "/plugins/FloatyChat";
            File directory = new File(directoryPath);

            if (directory.exists() && directory.isDirectory()) {
            } else {
                if (directory.mkdirs()) {
                } else {
                    System.out.println("Error with making plugin directory");
                }}
        }

    public static void checkFiles() {
        File jsonFile = new File(appDir + "/plugins/FloatyChat/list.json");
        File configFile = new File(appDir + "/plugins/FloatyChat/config.yaml");

        if (!jsonFile.exists()) {
            makeJsonFile(jsonFile);
        }
        if (!configFile.exists()) {
            try {
                makeConfigFile(configFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void makeJsonFile(File jsonFile){
        try {
            jsonFile.getParentFile().mkdirs();
            jsonFile.createNewFile();
            FileWriter writer = new FileWriter(jsonFile);
            writer.write("[]");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void makeConfigFile(File configFile) throws IOException {
        YamlWork.createYaml(configFile);
    }
}
