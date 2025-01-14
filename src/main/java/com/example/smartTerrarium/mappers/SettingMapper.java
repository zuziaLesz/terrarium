package com.example.smartTerrarium.mappers;

import com.example.smartTerrarium.dto.CreateSettingDto;
import com.example.smartTerrarium.entity.Setting;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Builder
@RequiredArgsConstructor
public class SettingMapper {

    private Setting mapCreateSettingDtoToSetting(CreateSettingDto createSettingDto) {
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
                .isCurrentlyUsed(false)
                .userId(1)
                .build();
    }
}
