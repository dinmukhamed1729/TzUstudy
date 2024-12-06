package din.tzustudy.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MeasurementRequest {
    private Double value;
    private boolean raining;
    private SensorDto sensor;
}
