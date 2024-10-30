package pl.matelkom.common;

import org.springframework.stereotype.Component;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileHandlerUtility {

    // Zapisuje plik CSV na dysku
    public void saveAsCSVonDisk(ArrayList<String> numbers, ArrayList<String> names, String filePath) {
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8")) {
            writer.write("\uFEFF"); // Dodaj BOM dla kompatybilności z Excel
            for (int i = 0; i < numbers.size(); i++) {
                writer.write(numbers.get(i) + ";" + names.get(i) + "\n"); // Tabulator jak to w CSV
            }
            System.out.println("CSV file saved to: " + filePath);
        } catch (IOException e) {
            System.out.println("Failed to save CSV file: " + e.getMessage());
        }
    }

    public ByteArrayOutputStream generateCsvStream(ArrayList<String> numbers, ArrayList<String> names) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8")) {
            writer.write("\uFEFF"); // Dodaj BOM dla kompatybilności z Excel
            for (int i = 0; i < numbers.size(); i++) {
                writer.write(numbers.get(i) + "\t" + names.get(i) + "\n"); // Tabulator jak to w CSV
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println("Failed to generate CSV: " + e.getMessage());
        }
        return outputStream;
    }
}
