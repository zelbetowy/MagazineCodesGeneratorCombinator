package pl.matelkom.web.CodesProcessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import pl.matelkom.common.ElementsCombinator;
import pl.matelkom.common.FileHandlerUtility;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

@Service
public class CodesProcessorService {

    private final ElementsCombinator elementsCombinator;
    private final FileHandlerUtility fileHandler;
    private final CsvFileRepository csvFileRepository;

    @Autowired
    public CodesProcessorService(ElementsCombinator elementsCombinator, FileHandlerUtility fileHandler, CsvFileRepository csvFileRepository) {
        this.elementsCombinator = elementsCombinator;
        this.fileHandler = fileHandler;
        this.csvFileRepository = csvFileRepository;
    }


    public GeneratedCSVFile processDataAndSaveToBase(Map<String, Object> requestData) {
        try {
            Map<String, ArrayList<String>> numbersMap = (Map<String, ArrayList<String>>) requestData.get("numbers");
            Map<String, ArrayList<String>> namesMap = (Map<String, ArrayList<String>>) requestData.get("names");
            String numbersSeparator = (String) requestData.get("numbersSeparator");
            String namesSeparator = (String) requestData.get("namesSeparator");

            ArrayList<String> numbersList = elementsCombinator.generateCombinations(numbersMap, numbersSeparator);
            ArrayList<String> namesList = elementsCombinator.generateCombinations(namesMap, namesSeparator);

            ByteArrayOutputStream byteArrayOutputStream = fileHandler.generateCsvStream(numbersList, namesList);
            byte[] byteArray = byteArrayOutputStream.toByteArray();

            GeneratedCSVFile generatedCSVFile = new GeneratedCSVFile();
            LocalDateTime now = LocalDateTime.now();
            String datePart = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            String uniquePart = UUID.randomUUID().toString();
            String fileName = "Generated_file_" + datePart + "_" + uniquePart + ".csv";

            generatedCSVFile.setFileName(fileName);
            generatedCSVFile.setCreatedAt(now);
            generatedCSVFile.setCsvData(byteArray);

            // Zapis do bazy
            generatedCSVFile = csvFileRepository.save(generatedCSVFile);

            return generatedCSVFile;


        } catch (DataAccessException e) {
            throw new RuntimeException("Błąd podczas zapisu do bazy danych: " + e.getMessage());
        } catch (Exception exception) {
            throw new RuntimeException("Wystąpił nieoczekiwany błąd: " + exception.getMessage());
        }
    }

}