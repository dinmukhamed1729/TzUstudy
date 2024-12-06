package din.tzustudy.service;

import din.tzustudy.model.dto.SensorDto;
import din.tzustudy.model.entyti.Sensor;
import din.tzustudy.repositories.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class SensorService {
    private final SensorRepository sensorRepository;

    public SensorDto registration(SensorDto sensorDto) {
        if (sensorRepository.existsSensorByName(sensorDto.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    String.format("A sensor with this name %s already exists ", sensorDto.getName()));
        }
        System.out.println("FFFFFFFFFFF");
        Sensor sensor = new Sensor(sensorDto.getName());
        sensorRepository.save(sensor);
        return new SensorDto(sensor.getName());
    }

    public Sensor findByName(String name) {
        return sensorRepository.findSensorByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("sensor with name %s not found", name)));
    }

    public void save(Sensor sensor) {
        sensorRepository.save(sensor);
    }
}
