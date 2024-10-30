package pl.matelkom.Runners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;


@Profile({"web-dev", "web-prod"})
@Service
public class WebRunner implements ProfileRunner {

    @Autowired
    private Environment environment; // Profile Check


    @Override
    public void start() {
        String webdev = "web-dev";
        String webprod = "web-prod";
        if (environment.getActiveProfiles().toString().contains(webdev)) {
            System.out.println("Profile " + webdev);
//            startServicesForDev();
        } else if (environment.getActiveProfiles().toString().contains(webprod)) {
            System.out.println("Profile " + webprod);
//            startServicesForProd();
        } else {
            System.out.println("No active profile detected.");
        }
    }


//    public void startServicesForDev() {
//        try {
//            String[] commands = {"npm", "run", "dev"};
//            startProcess(commands, "./frontendTypeScript");
//        } catch (IOException e) {
//            System.out.println("Problem starting frontend dev: " + e.getMessage());
//        }
//    }


}