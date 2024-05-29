package client;

import utils.ColorManager;
import utils.FileOperations;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

public class Client {
    private static final String CLIENTS_DIRECTORY = "clients/";

    private final String id;
    private final String filePath;
    private Person bride;
    private Person groom;
    private ContactInformation brideContactInfo;
    private ContactInformation groomContactInfo;
    private BigDecimal budget;

    public Client(Person bride, Person groom, ContactInformation brideContactInfo, ContactInformation groomContactInfo, BigDecimal budget) {
        this.bride = bride;
        this.groom = groom;
        this.brideContactInfo = brideContactInfo;
        this.groomContactInfo = groomContactInfo;
        this.budget = budget;
        this.id = generateUniqueId();
        this.filePath = CLIENTS_DIRECTORY + id + ".txt";
        saveToFile();
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(getPlainTextRepresentation());
        } catch (IOException e) {
            System.out.println(ColorManager.RED + "Failed to save client information to file." + ColorManager.RESET);
        }
    }

    private String getPlainTextRepresentation() {
        return "---------------------------------------------------------------------" + "\n" +
                "Client ID: " + id + "\n" + "\n" +
                "Bride Contact Info: " + "\n" +
                brideContactInfo.toPlainTextString() + "\n" + "\n" +
                "Groom Contact Info: " + "\n" +
                groomContactInfo.toPlainTextString() + "\n" + "\n" +
                "Budget: " + budget + "\n" +
                "---------------------------------------------------------------------";
    }

    private String generateInitials(ContactInformation contactInfo) {
        return String.format("%c%c",
                contactInfo.getPerson().getFirstName().charAt(0),
                contactInfo.getPerson().getLastName().charAt(0));
    }

    private String generatePhoneLast4(ContactInformation contactInfo) {
        return contactInfo.getPhone().substring(contactInfo.getPhone().length() - 4);
    }


    private String generateUniqueId() {
        String brideInitials = generateInitials(brideContactInfo);
        String bridePhoneLast4 = generatePhoneLast4(brideContactInfo);

        String groomInitials = generateInitials(groomContactInfo);
        String groomPhoneLast4 = generatePhoneLast4(groomContactInfo);

        return "id" + brideInitials + bridePhoneLast4 + groomInitials + groomPhoneLast4;
    }

    public String getId() {
        return id;
    }

    public Person getBride() {
        return bride;
    }

    public void setBride(Person bride) {
        this.bride = bride;
    }

    public Person getGroom() {
        return groom;
    }

    public void setGroom(Person groom) {
        this.groom = groom;
    }

    public ContactInformation getBrideContactInfo() {
        return brideContactInfo;
    }

    public void setBrideContactInfo(ContactInformation brideContactInfo) {
        this.brideContactInfo = brideContactInfo;
    }

    public ContactInformation getGroomContactInfo() {
        return groomContactInfo;
    }

    public void setGroomContactInfo(ContactInformation groomContactInfo) {
        this.groomContactInfo = groomContactInfo;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
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