package gui;

import java.io.*;

public class AppConfig2 {
    private static final String CONFIG_FILE_PATH = "config2.txt";

    public static String getAudioFilePath() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CONFIG_FILE_PATH))) {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public static void setAudioFilePath(String filePath2) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONFIG_FILE_PATH))) {
            writer.write(filePath2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
