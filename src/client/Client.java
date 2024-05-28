package client;

import utils.ColorManager;
import utils.FileOperations;

import java.math.BigDecimal;
import java.util.Set;

public class Client {
    private static final String ID_FILE = "client_ids.txt";
    private static final Set<String> ids = FileOperations.loadLines(ID_FILE);

    private final String id;
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

        String generatedId = "id" + brideInitials + bridePhoneLast4 + groomInitials + groomPhoneLast4;

        if (FileOperations.checkAndSaveLine(ids, generatedId, ID_FILE)) {
            return generatedId;
        } else {
            System.out.println(ColorManager.RED + "The ID " + generatedId + " is already taken." + ColorManager.RESET);
            return null;
        }
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