package pl.matelkom.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class ElementsCombinator {

    @Autowired
    private RestTemplate restTemplate;


    // Generowanie kombinacji dla podanych map z separatorami
    public ArrayList<String> generateCombinations(Map<String, ArrayList<String>> data, String separator) {
        ArrayList<String> keys = new ArrayList<>(data.keySet());
        ArrayList<List<String>> tableOfTables = new ArrayList<>(data.size());

        for (String key : keys) {
            tableOfTables.add(data.get(key));
        }

        ArrayList<String> combinations = new ArrayList<>();
        int[] indices = new int[tableOfTables.size()];

        while (true) {
            String eachCombination = IntStream.range(0, tableOfTables.size())
                    .mapToObj(j -> String.valueOf(tableOfTables.get(j).get(indices[j])))
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
}
