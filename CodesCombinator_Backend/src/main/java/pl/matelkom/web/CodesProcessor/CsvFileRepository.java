package pl.matelkom.web.CodesProcessor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CsvFileRepository extends JpaRepository<GeneratedCSVFile, Long> {
}
