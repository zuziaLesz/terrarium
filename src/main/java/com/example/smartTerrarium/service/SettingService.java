package com.example.smartTerrarium.service;

import com.example.smartTerrarium.dto.CreateSettingDto;
import com.example.smartTerrarium.dto.GetSettingDto;
import com.example.smartTerrarium.dto.TerrariumDataDto;
import com.example.smartTerrarium.entity.Setting;
import com.example.smartTerrarium.exception.NoCurrentSettingException;
import com.example.smartTerrarium.exception.SettingDoesNotExistException;
import com.example.smartTerrarium.exception.SettingIsNotCustomException;
import com.example.smartTerrarium.repository.SettingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SettingService {
    private SettingRepository settingRepository;

    @Autowired
    public SettingService(SettingRepository settingRepository) {
        this.settingRepository = settingRepository;
    }
    public void createSetting(CreateSettingDto createSettingDto) {
        Setting setting = buildSettingFromCreateSetting(createSettingDto);
        settingRepository.save(setting);
    }

    public void editSetting(Integer id, CreateSettingDto createSettingDto) {
        Setting setting = getSettingById(id);
        if(!setting.isCustom()) {
            throw new SettingIsNotCustomException("You cannot edit setting you did not make");
        }
        setting.setName(createSettingDto.getName());
        setting.setDescription(createSettingDto.getDescription());
        setting.setTemperature(createSettingDto.getTemperature());
        setting.setMoisture(createSettingDto.getMoisture());
        setting.setWaterOverWeek(createSettingDto.getWaterOverWeek());
        setting.setIrradiationStart(createSettingDto.getIrradiationStart());
        setting.setIrradiationStop(createSettingDto.getIrradiationStop());
        setting.setLastUpdated(new Date());
        setting.setUserId(1);  //add a user when user service is done
        settingRepository.save(setting);
    }

    public List<GetSettingDto> getAllSettings() {
        List<Setting> settingList = settingRepository.findAll();
        return settingList.stream()
                .map(this::mapSettingToDto)
                .collect(Collectors.toList());
    }

    public GetSettingDto getSetting(Integer id) {
        Setting setting = getSettingById(id);
        return mapSettingToDto(setting);
    }

    public void deleteSetting(Integer id) {
        Setting setting = getSettingById(id);
        if(!setting.isCustom()) {
            throw new SettingIsNotCustomException("You cannot delete setting you did not make");
        }
        settingRepository.deleteById(id);
    }
    public Setting getSettingById(Integer id) {
        return settingRepository.findById(id)
                .orElseThrow(() -> new SettingDoesNotExistException(id));
    }

    public TerrariumDataDto applySetting(Integer id) {
        changeCurrentSetting(id);
        Setting setting = getCurrentSetting();
        return TerrariumDataDto.builder()
                .temperature(setting.getTemperature())
                .moisture(setting.getMoisture())
                .build();
    }

    private void changeCurrentSetting(Integer id) {
        if(doesCurrentSettingExist()) {
            Setting previousSetting = getCurrentSetting();
            previousSetting.setCurrentlyUsed(false);
            settingRepository.save(previousSetting);
        }
        Setting setting = getSettingById(id);
        setting.setCurrentlyUsed(true);
        settingRepository.save(setting);
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
                .isCurrentlyUsed(false)
                .userId(1)  //add user when user service is done
                .build();
    }

    public GetSettingDto getCurrentSettingAndMap() {
        Setting setting = getCurrentSetting();
        return mapSettingToDto(setting);

    }
    public Setting getCurrentSetting() {
        return settingRepository.findCurrentlyUsed().orElseThrow(() -> new NoCurrentSettingException());
    }



    private GetSettingDto mapSettingToDto(Setting setting) {
        return GetSettingDto.builder()
                .id(setting.getId())
                .name(setting.getName())
                .description(setting.getDescription())
                .temperature(setting.getTemperature())
                .moisture(setting.getMoisture())
                .waterOverWeek(setting.getWaterOverWeek())
                .irradiationStart(setting.getIrradiationStart())
                .irradiationStop(setting.getIrradiationStop())
                .isCustom(setting.isCustom())
                .lastUpdated(setting.getLastUpdated())
                .isCurrentlyUsed(setting.isCurrentlyUsed())
                .userId(setting.getUserId())
                .build();
    }

    private boolean doesCurrentSettingExist() {
        Optional<Setting> currentSetting = settingRepository.findCurrentlyUsed();
        if(currentSetting.isPresent()){
            return true;
        }
        else return false;
    }


}
