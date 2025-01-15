//package com.example.smartTerrarium.config;
//
//import com.example.smartTerrarium.service.IrradiationService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.ApplicationListener;
//import org.springframework.stereotype.Component;
//
//@Component
//    @RequiredArgsConstructor
//    public class IrradiationSchedulerListener implements ApplicationListener<ApplicationReadyEvent> {
//
//        private final IrradiationService irradiationService;
//
//        @Override
//        public void onApplicationEvent(ApplicationReadyEvent event) {
//            irradiationService.scheduleIrradiationOn();
//        }
//    }
