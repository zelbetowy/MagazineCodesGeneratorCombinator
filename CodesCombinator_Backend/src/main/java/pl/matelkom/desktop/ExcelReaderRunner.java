package pl.matelkom.desktop;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


@Component
public class ExcelReaderRunner {

    private Process excelReader;
    private final String pythonPath = "./Portable Python-3.9.13 x64/App/Python/python.exe"; // Version for DekstopCompilled application
    private final String scriptPath = "./Microservices/ExcelReaderService/excel_reader.py";

    private ProcessBuilder getProcessBuilder() {
        File localPythonFile = new File(pythonPath);
        if (localPythonFile.exists()) {
            System.out.println("Using local Python at: " + pythonPath);
            return new ProcessBuilder(pythonPath, scriptPath);
        } else {
            System.out.println("Local Python not found. Using system Python.");
            return new ProcessBuilder("python", scriptPath);
        }
    }


    public void startExcelReader() {
        try {
            ProcessBuilder processBuilder = getProcessBuilder();

            processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);
            this.excelReader = processBuilder.start();
            System.out.println("Python script started successfully.");
        } catch (IOException e) {
            System.err.println("Error starting ExcelReader: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }

    public void killExcelReader() {
        if (excelReader.isAlive()) {
            System.out.println("Attempting to close all instances of ExcelReader...");
            try {
                // Zamknięcie wszystkich podprocesów
                excelReader.descendants().forEach(descendant -> {
                    if (descendant.isAlive()) {
                        descendant.destroy();
                        try {
                            descendant.onExit().get(5, TimeUnit.SECONDS); // Poczekanie
                            System.out.println("Successfully terminated a descendant process.");
                        } catch (Exception e) {
                            descendant.destroyForcibly(); // Wymuszenie zamknięcia
                            System.out.println("Forcibly terminated a descendant process.");
                        }
                    }
                });

                // Zamknięcie głównego procesu
                excelReader.getInputStream().close();
                excelReader.getErrorStream().close();
                excelReader.getOutputStream().close();
                excelReader.destroy();

                if (excelReader.isAlive()) {
                    excelReader.destroyForcibly();
                    System.out.println("Main process was forcibly terminated.");
                } else {
                    System.out.println("Main process terminated successfully.");
                }
            } catch (IOException e) {
                System.err.println("Failed to close streams for ExcelReader.");
                e.printStackTrace();
            }
        } else {
            System.out.println("ExcelReader is not running.");
        }
    }
}
