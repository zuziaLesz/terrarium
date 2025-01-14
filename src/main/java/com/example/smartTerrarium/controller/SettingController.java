package com.example.smartTerrarium.controller;

import com.example.smartTerrarium.dto.CreateSettingDto;
import com.example.smartTerrarium.dto.TerrariumDataDto;
import com.example.smartTerrarium.entity.Setting;
import com.example.smartTerrarium.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SettingController {
    private final SettingService settingService;
    @Autowired
    public SettingController(SettingService settingService) {
        this.settingService = settingService;
    }
    @PostMapping("/setting")
    public Setting createSetting(@RequestBody CreateSettingDto createSettingDto) {
        return settingService.createSetting(createSettingDto);
    }

    @PutMapping("/setting/{id}")
    public Setting editSetting(@PathVariable Integer id, @RequestBody CreateSettingDto createSettingDto) {
        return settingService.editSetting(id, createSettingDto);
    }

    @GetMapping("/setting/{id}")
    public ResponseEntity<Setting> getSettingById(@PathVariable Integer id) {
        return ResponseEntity.ok(settingService.getSettingById(id));
    }

    @GetMapping("/setting/current")
    public ResponseEntity<Setting> getCurrentSetting() {
        return ResponseEntity.ok(settingService.getCurrentSetting());
    }

    @PostMapping("/applySetting/{id}")
    public ResponseEntity<TerrariumDataDto> applySetting(@PathVariable Integer id) {
        return ResponseEntity.ok(settingService.applySetting(id));
        //check if ventilation should be turn on
    }
}
