package com.example.smartTerrarium.service;

import com.example.smartTerrarium.dto.SendTerrariumCommandDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class VentilationService {
    private final SettingService settingService;
    private final TerrariumStateService terrariumStateService;

    private String checkIfTurnOnVentilation(double moisture) {
        Double settingMoisture = settingService.getCurrentSetting().getMoisture();
        if(moisture>settingMoisture && !getVentilationStatus()) {
            terrariumStateService.changeVentilation(true);
            return "on";
        }
        else if(moisture<=settingMoisture && getVentilationStatus()) {
            terrariumStateService.changeVentilation(false);
            return "off";
        }
        else return null;
    }

    private SendTerrariumCommandDto buildCommand(String message) {
        SendTerrariumCommandDto command = new SendTerrariumCommandDto();
        command.setId(22);
        command.setCommand(message);
        return command;
    }

    public SendTerrariumCommandDto sendVentilationCommand(double moisture) {
        return buildCommand(checkIfTurnOnVentilation(moisture));
    }

    private boolean getVentilationStatus() {
        return terrariumStateService.getCurrentTerrariumState().isVentilation();
    }
}
