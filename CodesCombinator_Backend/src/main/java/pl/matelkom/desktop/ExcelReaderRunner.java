package pl.matelkom.desktop;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Profile("ConsoleDesktop")
@Component
public class ExcelReaderRunner {

    Process ExcelReader = null;

    public void startExcelReader() throws IOException {

        ProcessBuilder processBuilder = new ProcessBuilder(
                "python",
                "D:\\SOFT\\JAVA\\PartsCodesCombinatorGenerator\\src\\main\\resources\\PythonScripts\\excelReaderService\\excel_reader.py"
        );
        Process ExcelProcess = processBuilder.start();
        this.ExcelReader = ExcelProcess; // Przypisanie procesu do zmiennej dla ExcelReaderService instancji
        System.out.println("Python script started.");
    }

    public void killExcelReader() {

        if (ExcelReader != null) {
            ExcelReader.destroy();
        System.out.println("ExcelReader has been closed!"); }
    }
}
