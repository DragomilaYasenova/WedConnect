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
        } catch (IOException e) {
            e.printStackTrace();
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

    public static void checkAndSaveLine(String fileName, String newLine, Set<String> lines) {
        if (!lines.contains(newLine)) {
            saveLine(fileName, newLine);
            lines.add(newLine);
        }
    }

    public static void updateAccountInfo(String fileName, String targetLine, String newLine) {
        try {
            File file = new File(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuffer inputBuffer = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(targetLine)) {
                    inputBuffer.append(newLine);
                    inputBuffer.append('\n');
                } else {
                    inputBuffer.append(line);
                    inputBuffer.append('\n');
                }
            }
            reader.close();
            FileOutputStream fileOut = new FileOutputStream(file);
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFile(String fileName) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public static String readClientId(String fileName, String email, String password) {
        String lineToSearch = email + " : " + password + " : ";
        Set<String> accountInfo = loadLines(fileName);
        for (String info : accountInfo) {
            if (info.startsWith(lineToSearch)) {
                String[] parts = info.split(":");
                if (parts.length == 3) {
                    return parts[2].trim();
                }
            }
        }
        return null;
    }

    public static int extractBudgetFromFile(String fileName) {
        int budget = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Budget:")) {
                    String budgetString = line.split(":")[1].trim();
                    budget = Integer.parseInt(budgetString);
                    break;
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return budget;
    }
}