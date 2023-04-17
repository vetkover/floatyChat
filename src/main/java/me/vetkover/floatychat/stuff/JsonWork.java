package me.vetkover.floatychat.stuff;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonWork {
    static String appDir = System.getProperty("user.dir");
    static String JsonPath = appDir + "/plugins/FloatyChat/list.json";

    public static boolean deleteOneJson(String player) {
        try {
            JSONArray readArr = new JSONArray(new String(Files.readAllBytes(Paths.get(JsonPath))));
            boolean recordFound = false;

            for (int i = 0; i < readArr.length(); i++) {
                JSONObject record = readArr.getJSONObject(i);
                if (record.getString("player").equals(player)) {
                    readArr.remove(i);
                    recordFound = true;
                    break;
                }
            }
            if (recordFound) {
                FileWriter fileWriter = new FileWriter(JsonPath);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(readArr.toString());
                bufferedWriter.close();
                return true;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public static JSONObject findOneJson(String player) {
        Integer numberOfRecord = null;
        try {
            JSONArray readArr = new JSONArray(new String(Files.readAllBytes(Paths.get(JsonPath))));

            boolean recordFound = false;

            for (int i = 0; i < readArr.length(); i++) {
                JSONObject obj = readArr.getJSONObject(i);
                if (obj.getString("player").equals(player)) {
                    recordFound = true;
                    numberOfRecord = i;
                    break;
                }
            }
            if (recordFound) {
                JSONObject obj = readArr.getJSONObject(numberOfRecord);
                return obj;
            }
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void editOneJson(String player, String parameter, Object newValue) {
        try {
            JSONArray readArr = new JSONArray(new String(Files.readAllBytes(Paths.get(JsonPath))));

            boolean playerFound = false;
            int numberOfRecord = 0;
            for (int i = 0; i < readArr.length(); i++) {
                JSONObject obj = readArr.getJSONObject(i);
                if (obj.getString("player").equals(player)) {
                    playerFound = true;
                    numberOfRecord = i;
                    break;
                }
            }
            if (playerFound) {
                JSONObject obj = readArr.getJSONObject(numberOfRecord);
                obj.put(parameter, newValue);

                try (FileWriter fileWriter = new FileWriter(JsonPath);
                     BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                    bufferedWriter.write(readArr.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static JSONObject writeJson(String player, Integer punishTime, boolean firstEnter) {
        try {
            JSONArray arr = new JSONArray(new String(Files.readAllBytes(Paths.get(JsonPath))));

            boolean playerFound = false;
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                if (obj.getString("player").equals(player)) {
                    playerFound = true;
                    break;
                }
            }
            if(!playerFound) {
                JSONObject obj = new JSONObject();
                obj.put("player", player);
                obj.put("mute", punishTime);
                obj.put("notFirstEnter", firstEnter);
                arr.put(obj);
            } else {
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject obj = arr.getJSONObject(i);
                    if (obj.getString("player").equals(player)) {
                        obj.put("mute", punishTime);
                        obj.put("notFirstEnter", firstEnter);
                        break;
                    }
                }
            }
            try (FileWriter fileWriter = new FileWriter(JsonPath);
                 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                bufferedWriter.write(arr.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

