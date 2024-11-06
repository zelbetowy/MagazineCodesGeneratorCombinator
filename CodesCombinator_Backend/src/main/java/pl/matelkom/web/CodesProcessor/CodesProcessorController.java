package pl.matelkom.web.CodesProcessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.Map;

@Profile({"webdev", "webprod"})
@RestController
@RequestMapping("/api/codes")
public class CodesProcessorController {

    private final CodesProcessorService codesProcessorService;

    @Autowired
    public CodesProcessorController(CodesProcessorService codesProcessorService) {
        this.codesProcessorService = codesProcessorService;
    }


    @PostMapping("/generate")
    public ResponseEntity<InputStreamResource> generateCsv(@RequestBody Map<String, Object> requestData) {
        GeneratedCSVFile generatedCSVFile = codesProcessorService.processDataAndSaveToBase(requestData);

        // Utworzenie spowrotem strumienia z danych
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(generatedCSVFile.getCsvData());

        // Nagłówki dla odpowiedzi HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + generatedCSVFile.getFileName());
        headers.add("Content-Type", "text/csv");

        // Zwrócenie odpowiedzi z plikiem CSV
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(generatedCSVFile.getCsvData().length)
                .body(new InputStreamResource(byteArrayInputStream));
    }
}
