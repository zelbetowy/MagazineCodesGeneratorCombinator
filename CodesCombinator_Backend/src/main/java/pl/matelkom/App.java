package pl.matelkom;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.matelkom.Runners.AppStarter;


@SpringBootApplication(scanBasePackages = {"pl.matelkom"})
public class App {

	public static void main(String[] args)  {
		ConfigurableApplicationContext run = SpringApplication.run(App.class, args);
		AppStarter appStarter = run.getBean(AppStarter.class);
		appStarter.start();
	}
}





