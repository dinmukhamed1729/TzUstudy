package din.tzustudy.repositories;

import din.tzustudy.model.entyti.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<Sensor,Long> {
    public boolean existsSensorByName(String name);

    public Optional<Sensor> findSensorByName(String name);

}