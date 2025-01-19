package com.example.smartTerrarium.dto;

import lombok.*;

import java.time.LocalTime;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class GetSettingDto {
    private Integer id;
    private String name;
    private String description;
    private double temperature;
    private double moisture;
    private double waterOverWeek;
    private double frequency;
    private LocalTime irradiationStart;
    private LocalTime irradiationStop;
    private boolean isCustom;
    private Date lastUpdated;
    private boolean isCurrentlyUsed;
    private int userId;
}
