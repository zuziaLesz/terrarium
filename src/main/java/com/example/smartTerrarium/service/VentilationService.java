package com.example.smartTerrarium.service;

import org.springframework.stereotype.Service;

@Service
public class VentilationService {

    public boolean checkIfVentilationShouldBeTurnedOn(double moisture) {
        return true;
    }
}
