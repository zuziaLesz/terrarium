package com.example.smartTerrarium.controller;

import com.example.smartTerrarium.service.IrradiationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class IrradiationController {

    @Autowired
    private final IrradiationService irradiationService;

    @PostMapping("/lights/on")
    public ResponseEntity<Void> turnLightsOn() {
        irradiationService.scheduleIrradiationOn();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/lights/off")
    public ResponseEntity<Void> turnLightsOff() {
        irradiationService.scheduleIrradiationOff();
        return ResponseEntity.ok().build();
    }

}
