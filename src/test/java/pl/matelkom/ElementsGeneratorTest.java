package pl.matelkom;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


// Dodatkowe importy dla Mocka.
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;



public class ElementsGeneratorTest {

    @Mock
    private RestTemplate restTemplate;
    // Mockowanie RestTemplate

    @InjectMocks
    private ElementsCombinator elementsCombinator;
    // Automatyczne wstrzykiwanie mocka do testowanej klas

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        // Inicjalizacja Mockito
    }




    @Test
    public void testBolts() {
        // symulowana odpowiedz z Mockito
        when(restTemplate.getForEntity(eq("http://localhost:5000/read_bolts"), eq(Map.class)))
                .thenReturn(getMockedJsonResponse());

    // Uruchoienie testowej metody:
        elementsCombinator.bolts();
    }




    // Symulator odpowiedzi metody HTTP z mikroserwisu w formie JSONA
    private ResponseEntity<String> getMockedJsonResponse() {
        String rawJson = """
                {
                    "range1": [["00933", "00931", "06921", "00912", "07991", "73801", "73802"]],
                    "range2": [["N", "K", "T", "O", "C", "P", "A"]],
                    "range3": [["00", "55", 80, "10", "12", "PU", "PP"]],
                    "range4": [["040", "050", "060", "080", "100", "120", "140"]],
                    "range5": [["01m", "006", "008", "010", "012", "014", "016"]]
                } 
                """;

        // Montowanie odpowiedzi HTTP
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<String> stringResponseEntity = new ResponseEntity<>(rawJson, httpHeaders, HttpStatus.OK);  //Http Status 200 "OK";

        return stringResponseEntity;
    }
}





