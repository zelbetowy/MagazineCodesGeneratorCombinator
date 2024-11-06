package pl.matelkom.web.CodesProcessor;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Profile({"webdev", "webprod"})
@Repository
public interface CsvFileRepository extends JpaRepository<GeneratedCSVFile, Long> {
}
