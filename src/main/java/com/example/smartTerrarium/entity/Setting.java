package com.example.smartTerrarium.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Builder
@Access(AccessType.FIELD)
@Table(name = "setting")
public class Setting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private double temperature;
    private double moisture;
    @Column(name = "water_over_week")
    private double waterOverWeek;
    @Column(name = "irradiation_start")
    private LocalDateTime irradiationStart;
    @Column(name = "irradiation_stop")
    private LocalDateTime irradiationStop;
    @Column(name = "is_custom")
    private boolean isCustom;
    @Column(name = "last_updated")
    private Date lastUpdated;
    @Column(name = "is_currently_used")
    private boolean isCurrentlyUsed;
    @Column(name = "user_id")
    private int userId;
}
