package com.example.smartTerrarium.service;

import com.example.smartTerrarium.dto.CreateSettingDto;
import com.example.smartTerrarium.dto.TerrariumDataDto;
import com.example.smartTerrarium.entity.Setting;
import com.example.smartTerrarium.repository.SettingRepository;
import com.example.smartTerrarium.repository.TerrariumDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class SettingService {
    private SettingRepository settingRepository;

    @Autowired
    public SettingService(SettingRepository settingRepository) {
        this.settingRepository = settingRepository;
    }
    public Setting createSetting(CreateSettingDto createSettingDto) {
        Setting setting = buildSettingFromCreateSetting(createSettingDto);
        return settingRepository.save(setting);
    }

    public Setting editSetting(Integer id, CreateSettingDto createSettingDto) {
        Setting setting = getSettingById(id);
        setting.setName(createSettingDto.getName());
        setting.setDescription(createSettingDto.getDescription());
        setting.setTemperature(createSettingDto.getTemperature());
        setting.setMoisture(createSettingDto.getMoisture());
        setting.setWaterOverWeek(createSettingDto.getWaterOverWeek());
        setting.setIrradiationStart(createSettingDto.getIrradiationStart());
        setting.setIrradiationStop(createSettingDto.getIrradiationStop());
        setting.setLastUpdated(new Date());
        setting.setUserId(1);  //add a user when user service is done
        return settingRepository.save(setting);
    }

    public Setting getSettingById(Integer id) {
        return settingRepository.getById(id);
    }

    public TerrariumDataDto applySetting(Integer id) {
        Setting setting = getSettingById(id);
        setting.setCurrentlyUsed(true);
        return TerrariumDataDto.builder()
                .temperature(setting.getTemperature())
                .moisture(setting.getMoisture())
                .build();
    }
    private Setting buildSettingFromCreateSetting(CreateSettingDto createSettingDto) {
        return Setting.builder()
                .name(createSettingDto.getName())
                .description(createSettingDto.getDescription())
                .temperature(createSettingDto.getTemperature())
                .moisture(createSettingDto.getMoisture())
                .waterOverWeek(createSettingDto.getWaterOverWeek())
                .irradiationStart(createSettingDto.getIrradiationStart())
                .irradiationStop(createSettingDto.getIrradiationStop())
                .isCustom(true)
                .lastUpdated(new Date())
                .isCurrentlyUsed(true)
                .userId(1)  //add user when user service is done
                .build();
    }
    public Setting getCurrentSetting() {
        return settingRepository.findCurrentlyUsed().orElseThrow(() -> new RuntimeException("No currently used setting"));
    }


}
