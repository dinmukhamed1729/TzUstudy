package din.tzustudy.controllers;

import din.tzustudy.model.dto.MeasurementDto;
import din.tzustudy.model.dto.MeasurementRequest;
import din.tzustudy.model.entyti.Measurement;
import din.tzustudy.service.MeasurementService;
import din.tzustudy.service.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/measurements")
@RestController
public class MeasurementController {
    private final MeasurementService measurementService;
    private final SensorService sensorService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody MeasurementRequest measurementRequest) {
        var sensor = sensorService.findByName(measurementRequest.getSensor().getName());
        Measurement measurement = new Measurement(measurementRequest.getValue(), measurementRequest.isRaining());
        sensor.addMeasurement(measurement);
        measurementService.save(measurement);
        sensorService.save(sensor);
        return ResponseEntity.ok("measurement saved");
    }

    @GetMapping()
    public ResponseEntity<List<MeasurementDto>> getMeasurements() {
        return ResponseEntity.ok(measurementService.findAll());
    }

    @GetMapping("/rainyDaysCount")
    public ResponseEntity<Integer> getRainyDaysCount() {
        return ResponseEntity.ok(measurementService.getRainyDaysCount());
    }
}
