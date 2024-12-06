package din.tzustudy.controllers;

import din.tzustudy.model.dto.SensorDto;
import din.tzustudy.service.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/sensors")
@RestController
public class SensorController {

    private final SensorService sensorService;
    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody SensorDto sensorDto){
        return ResponseEntity.ok(sensorService.registration(sensorDto));
    }
}
