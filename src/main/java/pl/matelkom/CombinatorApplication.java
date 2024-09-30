package pl.matelkom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class CombinatorApplication implements CommandLineRunner {

	@Autowired
	private ExcelReaderService pythonService;

	public static void main(String[] args) {
		SpringApplication.run(CombinatorApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		//microservice - pythonExcelReader
		pythonService.startExcelReader();

		//mainController -> main loop
		MainController mainController = new MainController();
		mainController.start();

		//microservice killing - exit programm
		pythonService.stopExcelReader();

	}
}
