package pl.matelkom;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component
public class ElementsCombinator {

    @Autowired
    private RestTemplate restTemplate;

    public void nuts() {
        String pythonReaderHttp = "http://localhost:5000/read_nuts"; // URL dla nuts
        processJsonData(pythonReaderHttp);
    }

    public void washers() {
        String pythonReaderHttp = "http://localhost:5000/read_washers"; // URL dla washers
        processJsonData(pythonReaderHttp);
    }

    public void bolts() {
        String pythonReaderHttp = "http://localhost:5000/read_bolts"; // URL dla bolts
        processJsonData(pythonReaderHttp);
    }

    private void processJsonData(String url) {
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            String jsonBody = response.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> output;

            try {
                // Parsowanie do prostszej struktury Map<String, Object>
                output = objectMapper.readValue(
                        jsonBody,
                        new TypeReference<Map<String, Object>>() {
                        }
                );

                // Przetwarzanie każdego elementu mapy
                for (Map.Entry<String, Object> entry : output.entrySet()) {
                    List<List<String>> rangeData = (List<List<String>>) entry.getValue();
                    System.out.println("Key: " + entry.getKey() + ", Value: " + rangeData);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}