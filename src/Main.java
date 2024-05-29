import navigation.Navigation;
import utils.ColorManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Navigation navigation = new Navigation(scanner);

        System.out.println(ColorManager.PURPLE + "Welcome to WedConnect!" + ColorManager.RESET);

        navigation.menu();
    }
}