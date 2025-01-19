package com.example.smartTerrarium.service;

import com.example.smartTerrarium.dto.TerrariumStateDto;
import com.example.smartTerrarium.entity.TerrariumData;
import com.example.smartTerrarium.entity.TerrariumState;
import com.example.smartTerrarium.exception.NoTerrariumStateException;
import com.example.smartTerrarium.repository.TerrariumStateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TerrariumStateService {

    @Autowired
    private final TerrariumStateRepository terrariumStateRepository;

    public void addNewTerrariumState(TerrariumData terrariumData) {
        boolean irradiation;
        boolean ventilation;
        if(terrariumStateRepository.findMostRecent().isEmpty()) {
            irradiation = false;
            ventilation = false;
        }
        else {
            irradiation = getCurrentTerrariumState().isIrradiation();
            ventilation = getCurrentTerrariumState().isVentilation();
        }
        TerrariumState terrariumState =TerrariumState.builder()
                .temperature(terrariumData.getTemperature())
                .moisture(terrariumData.getMoisture())
                .irradiation(irradiation) //add irradiation when irradiation service works
                .ventilation(ventilation) //add ventilation when scheduled
                .lastUpdate(terrariumData.getLastUpdate())
                .build();
        terrariumStateRepository.save(terrariumState);
    }

    public TerrariumStateDto getCurrentTerrariumStateAndMapToDto() {
        TerrariumState terrariumState = getCurrentTerrariumState();
        return mapTerrariumStateToDto(terrariumState);
    }

    public TerrariumState getCurrentTerrariumState() {
        return terrariumStateRepository.findMostRecent().orElseThrow(() -> new NoTerrariumStateException());
    }

    public List<TerrariumStateDto> getAllTerrariumStates() {
        return terrariumStateRepository.findAll().stream()
                .map(this::mapTerrariumStateToDto)
                .collect(Collectors.toList());
    }

    public void changeIrradiation(boolean irradiation) {
        TerrariumState currentState = getCurrentTerrariumState();
        currentState.setIrradiation(irradiation);
        terrariumStateRepository.save(currentState);
    }

    public void changeVentilation(boolean ventilation) {
        TerrariumState currentState = getCurrentTerrariumState();
        currentState.setVentilation(ventilation);
        terrariumStateRepository.save(currentState);
    }

    private TerrariumStateDto mapTerrariumStateToDto(TerrariumState terrariumState) {
        return TerrariumStateDto.builder()
                .id(terrariumState.getId())
                .lastUpdate(terrariumState.getLastUpdate())
                .temperature(terrariumState.getTemperature())
                .moisture(terrariumState.getMoisture())
                .ventilation(terrariumState.isVentilation())
                .irradiation(terrariumState.isIrradiation())
                .build();
    }
}
