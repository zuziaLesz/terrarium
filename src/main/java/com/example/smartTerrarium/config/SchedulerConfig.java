//package com.example.smartTerrarium.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.TaskScheduler;
//import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
//import org.springframework.web.client.RestTemplate;
//
//@Configuration
//public class SchedulerConfig {
//    @Bean
//    public TaskScheduler taskScheduler() {
//        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
//        scheduler.setPoolSize(10); // Set the pool size, you can adjust this based on your needs
//        scheduler.setThreadNamePrefix("IrradiationScheduler-");
//        return scheduler;
//    }
//}
