package pl.matelkom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;





@SpringBootApplication
public class App {

	public static void main(String[] args)  {
		ConfigurableApplicationContext run = SpringApplication.run(App.class, args);
		AppStarter appStarter = run.getBean(AppStarter.class);
		appStarter.start();
	}
}





