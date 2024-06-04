package client;

import utils.ColorManager;
import utils.FileOperations;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public class Client {
    private final String id;
    private final Map<String, Object> brideInfo;
    private final Map<String, Object> groomInfo;
    private final BigDecimal budget;

    public Client(Map<String, Object> brideInfo, Map<String, Object> groomInfo, BigDecimal budget, IdGenerator idGenerator) {
        this.brideInfo = brideInfo;
        this.groomInfo = groomInfo;
        this.budget = budget;
        this.id = idGenerator.generateUniqueId((ContactInformation) brideInfo.get("contactInfo"), (ContactInformation) groomInfo.get("contactInfo"));
    }

    public String getId() {
        return id;
    }

    public Map<String, Object> getBrideInfo() {
        return brideInfo;
    }

    public Map<String, Object> getGroomInfo() {
        return groomInfo;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    @Override
    public String toString() {

        return "---------------------------------------------------------------------\n" +
                "Client ID: " + id + "\n\n" +
                "Bride Info: \n" +
                "Name: " + brideInfo.get("person") + "\n" +
                "Email: " + ((ContactInformation) brideInfo.get("contactInfo")).getEmail() + "\n" +
                "Phone: " + ((ContactInformation) brideInfo.get("contactInfo")).getPhone() + "\n" +
                "City: " + ((ContactInformation) brideInfo.get("contactInfo")).getCity() + "\n\n" +
                "Groom Info: \n" +
                "Name: " + groomInfo.get("person") + "\n" +
                "Email: " + ((ContactInformation) groomInfo.get("contactInfo")).getEmail() + "\n" +
                "Phone: " + ((ContactInformation) groomInfo.get("contactInfo")).getPhone() + "\n" +
                "City: " + ((ContactInformation) groomInfo.get("contactInfo")).getCity() + "\n\n" +
                "Budget: " + budget + "\n" +
                "---------------------------------------------------------------------";
    }
}