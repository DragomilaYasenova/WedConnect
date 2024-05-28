package utils;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class FileOperations {

    public static Set<String> loadLines(String fileName) {
        Set<String> lines = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line.trim());
            }
        } catch (IOException ignored) {

        }
        return lines;
    }

    public static void saveLine(String fileName, String line) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkAndSaveLine(Set<String> lines, String newLine, String fileName) {
        if (lines.contains(newLine)) {
            return false;
        } else {
            saveLine(fileName, newLine);
            lines.add(newLine);
            return true;
        }
    }
}