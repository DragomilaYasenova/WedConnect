package client;

import utils.ColorManager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ClientFileSaver {
    public void saveClientToFile(Client client, String directory) {
        String filePath = directory + client.getId() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(client.getPlainTextRepresentation());
        } catch (IOException e) {
            System.out.println(ColorManager.RED + "Failed to save client information to file." + ColorManager.RESET);
        }
    }
}
