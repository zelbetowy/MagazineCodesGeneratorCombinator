package pl.matelkom.desktop;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.matelkom.common.ElementsCombinator;
import pl.matelkom.common.FileHandlerUtility;

import java.util.ArrayList;
import java.util.Map;

@Service
public class ConsoleDesktopService {

    private final ElementsCombinator elementsCombinator;
    private final RestTemplate restTemplate;
    private final FileHandlerUtility fileSaverUtility;

    // Separatory, które można ustawiać z poziomu MainLoop
    @Setter
    @Getter
    private String NumbersSeparator = "-";

    @Setter
    @Getter
    private String NamesSeparator = " ";

    String filePathBolts = "DesktopGeneratedFiles/bolts_combinations.csv";
    String filePathNuts = "DesktopGeneratedFiles/nuts_combinations.csv";
    String filePathWashers = "DesktopGeneratedFiles/washers_combinations.csv";

    @Autowired
    public ConsoleDesktopService(ElementsCombinator elementsCombinator, RestTemplate restTemplate, FileHandlerUtility fileSaverUtility) {
        this.elementsCombinator = elementsCombinator;
        this.restTemplate = restTemplate;
        this.fileSaverUtility = fileSaverUtility;
    }

    public void bolts() {
        try {
            Map<String, ArrayList<String>> numbersMap = getDataFromMicroservice("http://localhost:5000/read_bolts_numbersBlock");
            Map<String, ArrayList<String>> namesMap = getDataFromMicroservice("http://localhost:5000/read_bolts_namesBlock");

            if (!mapsChecker(numbersMap, namesMap)) {
                throw new IllegalArgumentException("The number of numbers and names for generation is not identical.");
            }

            ArrayList<String> combinationsNumbers = elementsCombinator.generateCombinations(numbersMap, NumbersSeparator);
            ArrayList<String> combinationsNames = elementsCombinator.generateCombinations(namesMap, NamesSeparator);
            fileSaverUtility.saveAsCSVonDisk(combinationsNumbers, combinationsNames, filePathBolts);

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred during bolts processing: " + e.getMessage());
        }
    }

    public void nuts() {
        try {
            Map<String, ArrayList<String>> numbersMap = getDataFromMicroservice("http://localhost:5000/read_nuts_numbersBlock");
            Map<String, ArrayList<String>> namesMap = getDataFromMicroservice("http://localhost:5000/read_nuts_namesBlock");

            if (!mapsChecker(numbersMap, namesMap)) {
                throw new IllegalArgumentException("The number of numbers and names for generation is not identical.");
            }

            ArrayList<String> combinationsNumbers = elementsCombinator.generateCombinations(numbersMap, NumbersSeparator);
            ArrayList<String> combinationsNames = elementsCombinator.generateCombinations(namesMap, NamesSeparator);
            fileSaverUtility.saveAsCSVonDisk(combinationsNumbers, combinationsNames, filePathNuts);

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred during nuts processing: " + e.getMessage());
        }
    }

    public void washers() {
        try {
            Map<String, ArrayList<String>> numbersMap = getDataFromMicroservice("http://localhost:5000/read_washers_numbersBlock");
            Map<String, ArrayList<String>> namesMap = getDataFromMicroservice("http://localhost:5000/read_washers_namesBlock");

            if (!mapsChecker(numbersMap, namesMap)) {
                throw new IllegalArgumentException("The number of numbers and names for generation is not identical.");
            }

            ArrayList<String> combinationsNumbers = elementsCombinator.generateCombinations(numbersMap, NumbersSeparator);
            ArrayList<String> combinationsNames = elementsCombinator.generateCombinations(namesMap, NamesSeparator);
            fileSaverUtility.saveAsCSVonDisk(combinationsNumbers, combinationsNames, filePathWashers);

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred during washers processing: " + e.getMessage());
        }
    }

    // Method for getting data from microservice (excelData provider)
    private Map<String, ArrayList<String>> getDataFromMicroservice(String url) {
        try {
            return restTemplate.getForObject(url, Map.class);
        } catch (Exception e) {
            System.err.println("Failed to fetch data from microservice: " + e.getMessage());
            return null;
        }
    }

    // Method to check if data provided are correct.
    public boolean mapsChecker(Map<String, ArrayList<String>> map1, Map<String, ArrayList<String>> map2) {
        if (map1 == null || map2 == null) {
            return false; // Dodanie zabezpieczenia przed null
        }

        if (map1.size() != map2.size()) {
            return false;}

        for (String key : map1.keySet()) {
            if (!map2.containsKey(key) || map1.get(key).size() != map2.get(key).size()) {
                return false;}
        }
        return true;
    }
}