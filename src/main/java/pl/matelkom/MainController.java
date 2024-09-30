package pl.matelkom;

import java.util.Scanner;

public class MainController {


    public void start () {
        Scanner scanner = new Scanner(System.in);
        ElementsGenerator generator = new ElementsGenerator();

        boolean running = true;
        while (running) {

            System.out.println("Choose option:");
            System.out.println(" 1 - Generate bolts");
            System.out.println(" 2 - Generate nuts");
            System.out.println(" 3 - Generate washers");
            System.out.println(" 4 - Close programs");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Generating combinations for bolts");
                    generator.bolts();
                    break;
                case 2:
                    System.out.println("Generating combinations for nuts");
                    generator.nuts();
                    break;
                case 3:
                    System.out.println("Generating combinations for washers");
                    generator.washers();
                    break;
                case 4:
                    System.out.println("Closing program");
                    running = false;
                    break;

                default:
                    System.out.println("Wrong choice, try Again");

            }
        }
        scanner.close();
        System.out.println("bye!");
    }
}