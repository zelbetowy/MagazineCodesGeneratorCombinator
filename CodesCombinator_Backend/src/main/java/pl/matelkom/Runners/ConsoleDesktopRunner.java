package pl.matelkom.Runners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.matelkom.desktop.ConsoleDesktopService;
import pl.matelkom.desktop.ExcelReaderRunner;

import java.util.Scanner;

@Profile("desktop")
@Service
public class ConsoleDesktopRunner implements ProfileRunner{

    @Autowired
    private ExcelReaderRunner excelReaderRunner;
    @Autowired
    private ConsoleDesktopService consoleDesktopService;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void start() throws InterruptedException {
        startServices();
        Thread.sleep(200);
        mainLoop();
    }

    public void exit() {
        stopServices();
        SpringApplication.exit(applicationContext, () -> 0);
        System.exit(0);
    }

    public void mainLoop() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {

            System.out.println("Separators:");
            System.out.println("numbers separator: \"" + consoleDesktopService.getNumbersSeparator() + "\"");
            System.out.println("names separator: \"" + consoleDesktopService.getNamesSeparator() + "\"");

            System.out.println("Choose option:");
            System.out.println(" 4 - Generate bolts");
            System.out.println(" 5 - Generate nuts");
            System.out.println(" 6 - Generate washers");
            System.out.println(" 7 - Change numbers separator");
            System.out.println(" 8 - Change names separator");
            System.out.println(" 9 - Close programs");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 4:
                    System.out.println("Generating combinations for bolts");
                    consoleDesktopService.bolts();
                    break;
                case 5:
                    System.out.println("Generating combinations for nuts");
                    consoleDesktopService.nuts();
                    break;
                case 6:
                    System.out.println("Generating combinations for washers");
                    consoleDesktopService.washers();
                    break;
                case 7:
                    System.out.println("Enter new numbers separator");
                    String numbersSeparator = scanner.nextLine();
                    consoleDesktopService.setNumbersSeparator(numbersSeparator);
                    break;

                case 8:
                    System.out.println("Enter new names separator");
                    String namesSeparator = scanner.nextLine();
                    consoleDesktopService.setNamesSeparator(namesSeparator);
                    break;

                case 9:
                    System.out.println("Closing program");
                    running = false;
                    exit();
                    break;
                default:
                    System.out.println("Wrong choice, try Again");

            }
        }
    }

    public void startServices() {
        excelReaderRunner.startExcelReader();
    }

    public void stopServices() {
        excelReaderRunner.killExcelReader();
    }
}