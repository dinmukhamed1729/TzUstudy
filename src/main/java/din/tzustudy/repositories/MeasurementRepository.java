package din.tzustudy.repositories;

import din.tzustudy.model.entyti.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeasurementRepository extends JpaRepository<Measurement,Long> {
    Integer countByRainingTrue();
}
