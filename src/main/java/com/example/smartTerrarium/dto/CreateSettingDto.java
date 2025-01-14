package com.example.smartTerrarium.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateSettingDto {
    private String name;
    private String description;
    private double temperature;
    private double moisture;
    private double waterOverWeek;
    private LocalDateTime irradiationStart;
    private LocalDateTime irradiationStop;
}
