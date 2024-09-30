package pl.matelkom;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ExcelReaderService {

    private Process ExcelReaderProcess;

    public void startExcelReader() throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder("python", "path/to/your/python/script.py");
        ExcelReaderProcess = processBuilder.start();
        System.out.println("Python script started.");
    }

    public void stopExcelReader() {
        if (ExcelReaderProcess != null) {
            ExcelReaderProcess.destroy();
            System.out.println("Python script stopped.");
        }
    }
}

