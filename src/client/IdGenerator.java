package client;

public class IdGenerator {
    public String generateUniqueId(ContactInformation brideContactInfo, ContactInformation groomContactInfo) {
        String brideInitials = generateInitials(brideContactInfo);
        String bridePhoneLast4 = generatePhoneLast4(brideContactInfo);

        String groomInitials = generateInitials(groomContactInfo);
        String groomPhoneLast4 = generatePhoneLast4(groomContactInfo);

        return "id" + brideInitials + bridePhoneLast4 + groomInitials + groomPhoneLast4;
    }

    private String generateInitials(ContactInformation contactInfo) {
        return String.format("%c%c",
                contactInfo.getPerson().getFirstName().charAt(0),
                contactInfo.getPerson().getLastName().charAt(0));
    }

    private String generatePhoneLast4(ContactInformation contactInfo) {
        return contactInfo.getPhone().substring(contactInfo.getPhone().length() - 4);
    }
}
