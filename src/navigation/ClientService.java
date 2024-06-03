package navigation;

import account.Register;
import client.*;
import utils.ColorManager;
import utils.FileOperations;

import java.math.BigDecimal;
import java.util.Scanner;

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
        System.out.println("Enter bride's first name: ");
        String brideFirstName = scanner.nextLine();
        System.out.println("Enter bride's last name: ");
        String brideLastName = scanner.nextLine();
        Person bride = new Person(brideFirstName, brideLastName);

        System.out.println("Enter groom's first name: ");
        String groomFirstName = scanner.nextLine();
        System.out.println("Enter groom's last name: ");
        String groomLastName = scanner.nextLine();
        Person groom = new Person(groomFirstName, groomLastName);

        System.out.println("Enter bride's email: ");
        String brideEmail = scanner.nextLine();
        System.out.println("Enter bride's phone: ");
        String bridePhone = scanner.nextLine();
        System.out.println("Enter bride's city: ");
        String brideCity = scanner.nextLine();
        ContactInformation brideContactInfo = new ContactInformation(bride, brideEmail, bridePhone, brideCity);

        System.out.println("Enter groom's email: ");
        String groomEmail = scanner.nextLine();
        System.out.println("Enter groom's phone: ");
        String groomPhone = scanner.nextLine();
        System.out.println("Enter groom's city: ");
        String groomCity = scanner.nextLine();
        ContactInformation groomContactInfo = new ContactInformation(groom, groomEmail, groomPhone, groomCity);

        System.out.println("Enter budget: ");
        BigDecimal budget = new BigDecimal(scanner.nextLine());

        Client client = new Client(bride, groom, brideContactInfo, groomContactInfo, budget, idGenerator);


        String clientId = idGenerator.generateUniqueId(brideContactInfo, groomContactInfo);
        loggedInAccount.setClientId(clientId);
        loggedInAccount.saveAccount();

        loggedInAccount.setClient(client);
        clientFileSaver.saveClientToFile(client, "./clients/");
    }
}
