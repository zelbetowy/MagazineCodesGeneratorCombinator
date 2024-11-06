package pl.matelkom;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.matelkom.Runners.AppStarter;


@SpringBootApplication(scanBasePackages = {"pl.matelkom"})
public class App {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("=====================================");
		System.out.println("Welcome to CodesCombinator!");
		System.out.println("Running on Java version: " + System.getProperty("java.version"));
		System.out.println("Java vendor: " + System.getProperty("java.vendor"));
		System.out.println("Java home: " + System.getProperty("java.home"));
		System.out.println("=====================================");

		ConfigurableApplicationContext run = SpringApplication.run(App.class, args);
		AppStarter appStarter = run.getBean(AppStarter.class);
		appStarter.start();
	}
}





