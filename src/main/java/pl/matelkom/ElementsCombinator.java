package pl.matelkom;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class ElementsCombinator {

    @Autowired
    private RestTemplate restTemplate;

    public void bolts() {
        String Numbers_pythonReaderHttp = "http://localhost:5000/read_bolts_numbersBlock"; // URL dla boltsNumbers
        String Names_pythonReaderHttp = "http://localhost:5000/read_bolts_namesBlock"; // URL dla bolts

        Map<String, ArrayList<String>> numbersMap = processJSONincome(Numbers_pythonReaderHttp);
        Map<String, ArrayList<String>> namesMap = processJSONincome(Names_pythonReaderHttp);

        if (mapsChecker(numbersMap, namesMap)) {
            ArrayList<String> combinationsNumbers = generateCombinations(numbersMap, "-");
            ArrayList<String> combinationsNames = generateCombinations(namesMap, "-");
            String filePath = "DesktopGeneratedFiles/bolts_combinations.csv";
            sendtoCSV(combinationsNumbers, combinationsNames, filePath);
            System.out.println("Component names and numbers were correctly generated under the path:" + filePath );
        } else {
            System.out.println("The number of numbers and names for generation is not identical - check the lists in Excel - each number must have a corresponding name to generate a set.");
        }
    }

    public void nuts() {
        String Numbers_pythonReaderHttp = "http://localhost:5000/read_nuts_numbersBlock"; // URL dla nutsNumbers
        String Names_pythonReaderHttp = "http://localhost:5000/read_nuts_namesBlock"; // URL dla nuts

        Map<String, ArrayList<String>> numbersMap = processJSONincome(Numbers_pythonReaderHttp);
        Map<String, ArrayList<String>> namesMap = processJSONincome(Names_pythonReaderHttp);

        if (mapsChecker(numbersMap, namesMap)) {
            ArrayList<String> combinationsNumbers = generateCombinations(numbersMap, "-");
            ArrayList<String> combinationsNames = generateCombinations(namesMap, "-");
            String filePath = "DesktopGeneratedFiles/nuts_combinations.csv";
            sendtoCSV(combinationsNumbers, combinationsNames, filePath);
            System.out.println("Nuts combinations were correctly generated under the path: " + filePath);
        } else {
            System.out.println("The number of numbers and names for generation is not identical - check the lists in Excel - each number must have a corresponding name to generate a set.");
        }
    }

    public void washers() {
        String Numbers_pythonReaderHttp = "http://localhost:5000/read_washers_numbersBlock"; // URL dla washersNumbers
        String Names_pythonReaderHttp = "http://localhost:5000/read_washers_namesBlock"; // URL dla washers

        Map<String, ArrayList<String>> numbersMap = processJSONincome(Numbers_pythonReaderHttp);
        Map<String, ArrayList<String>> namesMap = processJSONincome(Names_pythonReaderHttp);

        if (mapsChecker(numbersMap, namesMap)) {
            ArrayList<String> combinationsNumbers = generateCombinations(numbersMap, "-");
            ArrayList<String> combinationsNames = generateCombinations(namesMap, "-");
            String filePath = "DesktopGeneratedFiles/washers_combinations.csv";
            sendtoCSV(combinationsNumbers, combinationsNames, filePath);

            System.out.println("Washers combinations were correctly generated under the path: " + filePath);
        } else {
            System.out.println("The number of numbers and names for generation is not identical - check the lists in Excel - each number must have a corresponding name to generate a set.");
        }
    }



    private Map<String, ArrayList<String>> processJSONincome(String url) {
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            String jsonBody = response.getBody();
            ObjectMapper objectMapper = new ObjectMapper();

            // Parsowanie JSON do Map<String, ArrayList<String>>
            Map<String, ArrayList<String>> output = null;
            try {
                output = objectMapper.readValue(
                        jsonBody,
                        new TypeReference<Map<String, ArrayList<String>>>() {
                        }
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
            return output;
        }
        return null;
    }

    public  ArrayList<String> generateCombinations(Map<String, ArrayList<String>> data, String separator) {
        ArrayList<String> keys = new ArrayList<>(data.keySet());
        ArrayList<List<String>> tableOfTables = new ArrayList<>(data.size());

        for (String key : keys) {
            tableOfTables.add(data.get(key));
        }



        ArrayList<String> combinations = new ArrayList<>();
        int[] indices = new int[tableOfTables.size()];

        while (true) {
            String eachCombination = IntStream.range(0, tableOfTables.size())
                    .mapToObj(j -> tableOfTables.get(j).get(indices[j]))
                    .collect(Collectors.joining(separator));
            combinations.add(eachCombination); // Dodawanie do listy wynikowej.

            // Aktualizacja indeksu
            int k = tableOfTables.size() - 1;
            while (k >= 0 && indices[k] == tableOfTables.get(k).size() - 1) {
                indices[k] = 0;
                k--;
            }

            if (k < 0) {
                break;
            }
            indices[k]++;
        }
        return combinations;
    }

    public  boolean mapsChecker(Map<String, ArrayList<String>> map1, Map<String, ArrayList<String>> map2) {
    if (map1.size() != map2.size()) {
        return false;
    }

    for (String key : map1.keySet()) {
        if (!map2.containsKey(key) || map1.get(key).size() != map2.get(key).size()) {
            return false;
        }
    }
    return true;
}

    private void sendtoCSV(ArrayList<String> numbers, ArrayList<String> names, String filePath) {
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8")) {
            writer.write("\uFEFF"); // Dodaj BOM na początku pliku
            for (int i = 0; i < numbers.size(); i++) {
                writer.write(numbers.get(i) + ";" + names.get(i) + "\n");
            }
        } catch (java.io.FileNotFoundException e) {
            System.out.println("Could not save CSV file. Probably another program is using it" + filePath);
        } catch (IOException e) {
            System.out.println("Failed to create CSV due to input/output error.");
            e.printStackTrace();
        }
    }
}






