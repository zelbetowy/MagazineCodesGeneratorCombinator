package pl.matelkom.web.CodesProcessor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;

@Profile({"webdev", "webprod"})
@Entity
public class GeneratedCSVFile {

    @Id
    @Generated
    private long ID;

    @Getter
    @Setter
    private String fileName;

    @Getter
    @Setter
    private LocalDateTime createdAt;

    @Getter
    @Setter
    private byte[] csvData;

    // Konstruktor bezargumentowy
    public GeneratedCSVFile() {}

    // Konstruktor z argumentami (opcjonalnie)
    public GeneratedCSVFile(String fileName, LocalDateTime createdAt) {
        this.fileName = fileName;
        this.createdAt = createdAt;
    }
}