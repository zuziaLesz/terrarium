package com.example.smartTerrarium.service;

import com.example.smartTerrarium.entity.Setting;
import com.example.smartTerrarium.repository.SettingRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class IrradiationService {

    private final TaskScheduler taskScheduler;
    private final SettingRepository settingRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public IrradiationService(TaskScheduler taskScheduler, SettingRepository settingRepository, RestTemplate restTemplate) {
        this.taskScheduler = taskScheduler;
        this.settingRepository = settingRepository;
        this.restTemplate = restTemplate;
        scheduleIrradiationOn();
    }

    private Setting getCurrentSetting() {
        return settingRepository.findCurrentlyUsed().orElseThrow(() -> new RuntimeException("No currently used setting"));
    }

    public void scheduleIrradiationOn() {
        Instant timeIrradiationStart = getStartIrradiationTime().toInstant(ZoneOffset.of("+01:00"));
        taskScheduler.schedule(this::sendPostRequestToTurnIrradiationOn, timeIrradiationStart);
    }

    public void scheduleIrradiationOff() {
        Instant timeIrradiationEnds = getEndIrradiationTime().toInstant(ZoneOffset.of("+01:00"));
        taskScheduler.schedule(this::sendPostRequestToTurnIrradiationOff, timeIrradiationEnds);
    }

    public void sendPostRequestToTurnIrradiationOff() {
        String irradiationUrlOff = "http://localhost:8080/lights/off";
        restTemplate.postForEntity(irradiationUrlOff, false, Void.class);

    }

    public void sendPostRequestToTurnIrradiationOn() {
        String irradiationUrlOn = "http://localhost:8080/lights/on";
        restTemplate.postForEntity(irradiationUrlOn, true, Void.class);
    }

    private LocalDateTime getStartIrradiationTime() {
        Setting setting = getCurrentSetting();
        return setting.getIrradiationStart();
    }
    private LocalDateTime getEndIrradiationTime() {
        Setting setting = getCurrentSetting();
        return setting.getIrradiationStop();
    }

}
