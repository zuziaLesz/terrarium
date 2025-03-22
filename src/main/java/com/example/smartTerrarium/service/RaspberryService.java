package com.example.smartTerrarium.service;

import com.example.smartTerrarium.dto.RaspberrySetting;
import com.example.smartTerrarium.dto.RaspberryState;
import com.example.smartTerrarium.entity.Setting;
import com.example.smartTerrarium.entity.TerrariumState;
import com.example.smartTerrarium.repository.TerrariumStateRepository;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Date;
@RequiredArgsConstructor
@Service
public class RaspberryService {
    @Autowired
    private final TerrariumStateRepository terrariumStateRepository;
    private final SettingService settingService;
    public void saveState(RaspberryState state) {
        TerrariumState terrariumState = TerrariumState.builder()
                .lastUpdate(new Date())
                .temperature(state.getTemperature())
                .moisture(state.getHumidity())
                .ventilation(false)
                .irradiation(false)
                .build();
        terrariumStateRepository.save(terrariumState);
    }
    public RaspberrySetting sendSetting() {
        RaspberrySetting setting = new RaspberrySetting();
        Setting currentSetting = settingService.getCurrentSetting();
        setting.setTemperature(currentSetting.getTemperature());
        setting.setHumidity(currentSetting.getMoisture());
        return setting;
    }
}
