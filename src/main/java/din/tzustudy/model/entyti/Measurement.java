package din.tzustudy.model.entyti;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double value;
    private boolean raining;

    @ManyToOne
    @JoinColumn(name = "sensor_id",nullable = false)
    private Sensor sensor;
    public Measurement(Double value,boolean raining){
        this.value = value;
        this.raining = raining;
    }
}
