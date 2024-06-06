package navigation;

import account.Register;
import client.*;

import exceptions.client.InvalidFirstNameException;
import exceptions.client.InvalidLastNameException;
import exceptions.client.InvalidEmailAddressException;
import exceptions.client.InvalidPhoneNumberException;
import exceptions.client.InvalidCityFormatException;
import utils.ColorManager;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;

public class ClientService {
    private final IdGenerator idGenerator;
    private final ClientFileSaver clientFileSaver;

    public ClientService(IdGenerator idGenerator, ClientFileSaver clientFileSaver) {
        this.idGenerator = idGenerator;
        this.clientFileSaver = clientFileSaver;
    }

    public void addClientMenu(Scanner scanner, Register loggedInAccount) {
        if (loggedInAccount.getClient() != null) {
            System.out.println(ColorManager.RED + "Client information already exists for this account." + ColorManager.RESET);
            return;
        }

        Map<String, Object> brideInfo = new HashMap<>();
        String brideFirstName;
        String brideLastName;
        String brideEmail;
        String bridePhone;
        String brideCity;

        while (true) {
            try {
                System.out.println("Enter bride's first name: ");
                brideFirstName = scanner.nextLine();
                new Person(brideFirstName, "Test");
                break;
            } catch (InvalidFirstNameException e) {
                System.out.println(ColorManager.RED + e.getMessage() + ColorManager.RESET);
            }
        }

        while (true) {
            try {
                System.out.println("Enter bride's last name: ");
                brideLastName = scanner.nextLine();
                new Person(brideFirstName, brideLastName);
                break;
            } catch (InvalidLastNameException e) {
                System.out.println(ColorManager.RED + e.getMessage() + ColorManager.RESET);
            }
        }

        while (true) {
            try {
                System.out.println("Enter bride's email: ");
                brideEmail = scanner.nextLine();
                new ContactInformation(new Person(brideFirstName, brideLastName), brideEmail, "0000000000", "Test");
                break;
            } catch (InvalidEmailAddressException e) {
                System.out.println(ColorManager.RED + e.getMessage() + ColorManager.RESET);
            }
        }

        while (true) {
            try {
                System.out.println("Enter bride's phone: ");
                bridePhone = scanner.nextLine();
                new ContactInformation(new Person(brideFirstName, brideLastName), brideEmail, bridePhone, "Test");
                break;
            } catch (InvalidPhoneNumberException e) {
                System.out.println(ColorManager.RED + e.getMessage() + ColorManager.RESET);
            }
        }

        while (true) {
            try {
                System.out.println("Enter bride's city: ");
                brideCity = scanner.nextLine();
                new ContactInformation(new Person(brideFirstName, brideLastName), brideEmail, bridePhone, brideCity);
                break;
            } catch (InvalidCityFormatException e) {
                System.out.println(ColorManager.RED + e.getMessage() + ColorManager.RESET);
            }
        }

        Person bride = new Person(brideFirstName, brideLastName);
        ContactInformation brideContactInfo = new ContactInformation(bride, brideEmail, bridePhone, brideCity);
        brideInfo.put("person", bride);
        brideInfo.put("contactInfo", brideContactInfo);

        Map<String, Object> groomInfo = new HashMap<>();
        String groomFirstName;
        String groomLastName;
        String groomEmail;
        String groomPhone;
        String groomCity;

        while (true) {
            try {
                System.out.println("Enter groom's first name: ");
                groomFirstName = scanner.nextLine();
                new Person(groomFirstName, "Test");
                break;
            } catch (InvalidFirstNameException e) {
                System.out.println(ColorManager.RED + e.getMessage() + ColorManager.RESET);
            }
        }

        while (true) {
            try {
                System.out.println("Enter groom's last name: ");
                groomLastName = scanner.nextLine();
                new Person(groomFirstName, groomLastName);
                break;
            } catch (InvalidLastNameException e) {
                System.out.println(ColorManager.RED + e.getMessage() + ColorManager.RESET);
            }
        }

        while (true) {
            try {
                System.out.println("Enter groom's email: ");
                groomEmail = scanner.nextLine();
                new ContactInformation(new Person(groomFirstName, groomLastName), groomEmail, "0000000000", "Test");
                break;
            } catch (InvalidEmailAddressException e) {
                System.out.println(ColorManager.RED + e.getMessage() + ColorManager.RESET);
            }
        }

        while (true) {
            try {
                System.out.println("Enter groom's phone: ");
                groomPhone = scanner.nextLine();
                new ContactInformation(new Person(groomFirstName, groomLastName), groomEmail, groomPhone, "Test");
                break;
            } catch (InvalidPhoneNumberException e) {
                System.out.println(ColorManager.RED + e.getMessage() + ColorManager.RESET);
            }
        }

        while (true) {
            try {
                System.out.println("Enter groom's city: ");
                groomCity = scanner.nextLine();
                new ContactInformation(new Person(groomFirstName, groomLastName), groomEmail, groomPhone, groomCity);
                break;
            } catch (InvalidCityFormatException e) {
                System.out.println(ColorManager.RED + e.getMessage() + ColorManager.RESET);
            }
        }

        Person groom = new Person(groomFirstName, groomLastName);
        ContactInformation groomContactInfo = new ContactInformation(groom, groomEmail, groomPhone, groomCity);
        groomInfo.put("person", groom);
        groomInfo.put("contactInfo", groomContactInfo);

        System.out.println("Enter budget: ");
        BigDecimal budget = new BigDecimal(scanner.nextLine());

        Client client = new Client(brideInfo, groomInfo, budget, idGenerator);

        String clientId = idGenerator.generateUniqueId(brideContactInfo, groomContactInfo);
        loggedInAccount.setClientId(clientId);
        loggedInAccount.saveAccount();

        loggedInAccount.setClient(client);
        clientFileSaver.saveClientToFile(client, "./clients/");

        System.out.println(ColorManager.GREEN + "Client information successfully added." + ColorManager.RESET);
    }
}