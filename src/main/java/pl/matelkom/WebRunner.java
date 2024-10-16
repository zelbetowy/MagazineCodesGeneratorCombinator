package pl.matelkom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Profile({"web-dev", "web-prod"})
@Service
public class WebRunner implements ProfileRunner {

    @Autowired
    private Environment environment; // Profile CHeck

    // List for processes started
    private List<Process> processes = new ArrayList<>();

    @Override
    public void start() {
        if (environment.getActiveProfiles().toString().contains("web-dev")) {
            startServicesForDev();
        } else if (environment.getActiveProfiles().toString().contains("web-prod")) {
            startServicesForProd();
        } else {
            System.out.println("No active profile detected.");
        }
    }

    public void startServicesForDev() {
        try {
            String[] commands = {"npm", "run", "dev"};
            startProcess(commands, "./frontendTypecript");
        } catch (IOException e) {
            System.out.println("Problem starting frontend dev: " + e.getMessage());
        }
    }

    public void startServicesForProd() {
        try {
            String[] commands = {"npm", "run", "dev"};
            startProcess(commands, "./frontendTypecript");
        } catch (IOException e) {
            System.out.println("Problem starting frontend prod: " + e.getMessage());
        }
    }

    private void startProcess(String[] commands, String path) throws IOException {
        System.out.println("Starting process with command: " + String.join(" ", commands) + " in path: " + path);

        try {
            ProcessBuilder builder = new ProcessBuilder(commands);
            if (path != null && !path.isEmpty()) {
                builder.directory(new java.io.File(path));
            }

            Process process = builder.start();
            System.out.println("Process started with command: " + String.join(" ", commands) + " in path: " + path);

            if (process.isAlive()) {
                processes.add(process);
                System.out.println("Process is alive and added to the list.");
            } else {
                System.out.println("Process failed to start.");
            }

        } catch (IOException e) {
            System.out.println("Failed to start process with command: " + String.join(" ", commands));
            e.printStackTrace();

        } catch (Exception e) {
            System.out.println("Unexpected error occurred while starting process.");
            e.printStackTrace();
        }
    }

    public void stopServices() {
        for (Process process : processes) {
            if (process.isAlive()) {
                process.destroy();
                System.out.println("Process stopped.");
            }
        }
        processes.clear();
        System.out.println("All processes stopped.");
    }
}