package pl.matelkom.web.CodesProcessor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
public class GeneratedCSVFile {

    @Id
    @Generated
    private long ID;
    private String fileName;
    private LocalDateTime createdAt;
    private byte[] csvData;

    // Konstruktor bezargumentowy
    public GeneratedCSVFile() {}

    // Konstruktor z argumentami (opcjonalnie)
    public GeneratedCSVFile(String fileName, LocalDateTime createdAt) {
        this.fileName = fileName;
        this.createdAt = createdAt;
    }
}