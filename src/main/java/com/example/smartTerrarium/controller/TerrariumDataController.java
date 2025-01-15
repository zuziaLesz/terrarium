package com.example.smartTerrarium.controller;

import com.example.smartTerrarium.dto.TerrariumDataDto;
import com.example.smartTerrarium.entity.TerrariumData;
import com.example.smartTerrarium.service.SettingService;
import com.example.smartTerrarium.service.TerrariumDataService;
import com.example.smartTerrarium.service.TerrariumStateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TerrariumDataController {
    @Autowired
    private final TerrariumDataService terrariumDataService;
    private final TerrariumStateService terrariumStateService;
    private final SettingService settingService;

    @GetMapping("/dataTerrarium")
    public ResponseEntity<TerrariumData> getTemperatureFromTerrarium(@RequestBody TerrariumDataDto terrariumDataDto) {
            TerrariumData terrariumData = terrariumDataService.saveTerrariumData(terrariumDataDto);
            terrariumStateService.addNewTerrariumState(terrariumData);
            settingService.checkIfTurnOnVentilation(terrariumDataDto.getMoisture()); //ventylator
            return ResponseEntity.ok(terrariumData);
    }

}
