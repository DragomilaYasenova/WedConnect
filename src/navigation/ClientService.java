package navigation;

import account.Register;
import client.*;
import exceptions.account.AccountAlreadyExistsException;
import exceptions.password.PasswordCannotBeNullException;
import exceptions.password.PasswordsDoNotMatchException;
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

    public void addClientMenu(Scanner scanner, Register loggedInAccount) throws PasswordsDoNotMatchException, AccountAlreadyExistsException, PasswordCannotBeNullException {
        if (loggedInAccount.getClient() != null) {
            System.out.println(ColorManager.RED + "Client information already exists for this account." + ColorManager.RESET);
            return;
        }

        Map<String, Object> brideInfo = new HashMap<>();

        System.out.println("Enter bride's first name: ");
        String brideFirstName = scanner.nextLine();
        System.out.println("Enter bride's last name: ");
        String brideLastName = scanner.nextLine();
        Person bride = new Person(brideFirstName, brideLastName);
        brideInfo.put("person", bride);

        System.out.println("Enter bride's email: ");
        String brideEmail = scanner.nextLine();
        System.out.println("Enter bride's phone: ");
        String bridePhone = scanner.nextLine();
        System.out.println("Enter bride's city: ");
        String brideCity = scanner.nextLine();
        ContactInformation brideContactInfo = new ContactInformation(bride, brideEmail, bridePhone, brideCity);
        brideInfo.put("contactInfo", brideContactInfo);

        Map<String, Object> groomInfo = new HashMap<>();

        System.out.println("Enter groom's first name: ");
        String groomFirstName = scanner.nextLine();
        System.out.println("Enter groom's last name: ");
        String groomLastName = scanner.nextLine();
        Person groom = new Person(groomFirstName, groomLastName);
        groomInfo.put("person", groom);

        System.out.println("Enter groom's email: ");
        String groomEmail = scanner.nextLine();
        System.out.println("Enter groom's phone: ");
        String groomPhone = scanner.nextLine();
        System.out.println("Enter groom's city: ");
        String groomCity = scanner.nextLine();
        ContactInformation groomContactInfo = new ContactInformation(groom, groomEmail, groomPhone, groomCity);
        groomInfo.put("contactInfo", groomContactInfo);

        System.out.println("Enter budget: ");
        BigDecimal budget = new BigDecimal(scanner.nextLine());

        Client client = new Client(brideInfo, groomInfo, budget, idGenerator);


        String clientId = idGenerator.generateUniqueId(brideContactInfo, groomContactInfo);
        loggedInAccount.setClientId(clientId);
        loggedInAccount.saveAccount();

        loggedInAccount.setClient(client);
        clientFileSaver.saveClientToFile(client, "./clients/");
    }
}
