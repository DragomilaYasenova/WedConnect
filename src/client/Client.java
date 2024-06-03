package client;

import utils.ColorManager;
import utils.FileOperations;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

public class Client {
    private final String id;
    private final Person bride;
    private final Person groom;
    private final ContactInformation brideContactInfo;
    private final ContactInformation groomContactInfo;
    private final BigDecimal budget;

    public Client(Person bride, Person groom, ContactInformation brideContactInfo, ContactInformation groomContactInfo, BigDecimal budget, IdGenerator idGenerator) {
        this.bride = bride;
        this.groom = groom;
        this.brideContactInfo = brideContactInfo;
        this.groomContactInfo = groomContactInfo;
        this.budget = budget;
        this.id = idGenerator.generateUniqueId(brideContactInfo, groomContactInfo);
    }

    public String getPlainTextRepresentation() {
        return "---------------------------------------------------------------------" + "\n" +
                "Client ID: " + id + "\n" + "\n" +
                "Bride Contact Info: " + "\n" +
                brideContactInfo.toString() + "\n" + "\n" +
                "Groom Contact Info: " + "\n" +
                groomContactInfo.toString() + "\n" + "\n" +
                "Budget: " + budget + "\n" +
                "---------------------------------------------------------------------";
    }
    public String getId() {
        return id;
    }

    public Person getBride() {
        return bride;
    }

    public Person getGroom() {
        return groom;
    }


    public ContactInformation getBrideContactInfo() {
        return brideContactInfo;
    }


    public ContactInformation getGroomContactInfo() {
        return groomContactInfo;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    @Override
    public String toString() {
        return "---------------------------------------------------------------------" + "\n" +
                "Client ID: " + ColorManager.YELLOW + id + ColorManager.RESET + "\n" + "\n" +
                "Bride Contact Info: " + "\n" +
                brideContactInfo + "\n" +
                "Groom Contact Info: " + "\n" +
                groomContactInfo + "\n" +
                "Budget: " + ColorManager.YELLOW + budget + ColorManager.RESET + "\n" +
                "---------------------------------------------------------------------";
    }
}