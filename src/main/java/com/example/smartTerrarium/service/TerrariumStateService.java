package com.example.smartTerrarium.service;

import com.example.smartTerrarium.entity.TerrariumData;
import com.example.smartTerrarium.entity.TerrariumState;
import com.example.smartTerrarium.repository.TerrariumDataRepository;
import com.example.smartTerrarium.repository.TerrariumStateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TerrariumStateService {

    @Autowired
    private final TerrariumStateRepository terrariumStateRepository;

    public void addNewTerrariumState(TerrariumData terrariumData) {
        TerrariumState terrariumState =TerrariumState.builder()
                .temperature(terrariumData.getTemperature())
                .moisture(terrariumData.getMoisture())
                .irradiation(true) //add irradiation when irradiation service works
                .ventilation(true) //add ventilation when scheduled
                .lastUpdate(terrariumData.getLastUpdate())
                .build();
        terrariumStateRepository.save(terrariumState);
    }

    public List<TerrariumState> getAllTerrariumStates() {
        return terrariumStateRepository.findAll();
    }
}
