package pl.matelkom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Scanner;

@Profile("desktop")
@Service
public class DesktopRunner implements ProfileRunner{

    @Autowired
    ExcelReaderService excelReaderService;
    @Autowired
    ElementsCombinator generator;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void start() {
        startServices();
        mainLoop();
    }

    public void exit () {
        stopServices();
        SpringApplication.exit(applicationContext, () -> 0);
        System.exit(0);

    }


    public void mainLoop() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Choose option:");
            System.out.println(" 4 - Generate bolts");
            System.out.println(" 5 - Generate nuts");
            System.out.println(" 6 - Generate washers");
            System.out.println(" 7 - Close programs");
            int choice = scanner.nextInt();

            switch (choice) {
                case 4:
                    System.out.println("Generating combinations for bolts");
                    generator.bolts();
                    break;
                case 5:
                    System.out.println("Generating combinations for nuts");
                    generator.nuts();
                    break;
                case 6:
                    System.out.println("Generating combinations for washers");
                    generator.washers();
                    break;
                case 7:
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
        try {
            excelReaderService.startExcelReader();
        } catch (IOException e) {
            System.out.println(" Problem with " + excelReaderService.toString());
            throw new RuntimeException(e);
        }
    }

    public void stopServices() {
        excelReaderService.killExcelReader();
    }
}