package din.tzustudy.service;

import din.tzustudy.model.dto.MeasurementDto;
import din.tzustudy.model.dto.SensorDto;
import din.tzustudy.model.entyti.Measurement;
import din.tzustudy.repositories.MeasurementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MeasurementService {
    private final MeasurementRepository measurementRepository;

    public void save(Measurement measurement) {
        measurementRepository.save(measurement);
    }

    public List<MeasurementDto> findAll() {
        return measurementRepository.findAll()
                .stream().map(x -> new MeasurementDto(x.getValue(), x.isRaining(), new SensorDto(x.getSensor().getName()))).toList();

    }

    public Integer getRainyDaysCount() {
        return measurementRepository.countByRainingTrue();
    }
}
