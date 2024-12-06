package din.tzustudy.model.entyti;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "sensors")
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Measurement> measurements;

    public Sensor(String name) {
        this.name = name;
    }

    public void addMeasurement(Measurement measurement){
        measurements.add(measurement);
        measurement.setSensor(this);
    }
}
