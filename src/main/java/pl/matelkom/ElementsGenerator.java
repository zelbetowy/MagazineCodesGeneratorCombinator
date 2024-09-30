package pl.matelkom;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import org.json.JSONObject;


public class ElementsGenerator {

    public ElementsGenerator () {
    };

    public void bolts () {

        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest.Builder builder = HttpRequest.newBuilder().uri(new URI("http://localhost:5000/get_data_bolts")).GET().build();

            httpClient.send(r)


        }




        String[] array1 = {"00913"};
        String[] array2 = {"N"};
        String[] array3 = {"00"};
        String[] array4 = {"060","080","100","120"};
        String[] array5 = {"020","025","030","045","050","055"};

        // Create a list to hold the combinations
        List<List<String>> combinations = new ArrayList<>();

        // Generate all combinations of elements from the five arrays
        for (String element1 : array1) {
            for (String element2 : array2) {
                for (String element3 : array3) {
                    for (String element4 : array4) {
                        for (String element5 : array5) {
                            List<String> combination = new ArrayList<>();
                            combination.add(element1);
                            combination.add(element2);
                            combination.add(element3);
                            combination.add(element4);
                            combination.add(element5);
                            combinations.add(combination);
                        }
                    }
                }
            }
        }

        // Clean up the output and save it to a text file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("combinationsbolt.txt"))) {
            for (List<String> combination : combinations) {
                String cleanedUpCombination = combination.toString()
                        .replaceAll("\\[", "") // Remove the opening bracket
                        .replaceAll("\\]", "") // Remove the closing bracket
                        .replaceAll(",", "-") // Remove the commas
                        .replaceAll("  ", "") // Replace double spaces with nothing
                        .replaceAll(" ", ""); // Replace double spaces with nothing
                writer.write(cleanedUpCombination);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void nuts() {
    }

    public void washers() {
    }
}

