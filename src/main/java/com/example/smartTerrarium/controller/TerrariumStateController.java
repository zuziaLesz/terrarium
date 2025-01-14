package com.example.smartTerrarium.controller;

import com.example.smartTerrarium.entity.TerrariumState;
import com.example.smartTerrarium.service.TerrariumStateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestControllerg
@RequiredArgsConstructor
public class TerrariumStateController {
    @Autowired
    private final TerrariumStateService terrariumStateService;

    @GetMapping("/terrarium_state")
    public ResponseEntity<List<TerrariumState>> getTerrariumState() {
        return ResponseEntity.ok(terrariumStateService.getAllTerrariumStates());
    }
}
