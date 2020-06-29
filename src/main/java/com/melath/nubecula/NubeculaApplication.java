package com.melath.nubecula;

import com.melath.nubecula.security.service.UserStorageService;
import com.melath.nubecula.storage.config.StorageProperties;
import com.melath.nubecula.storage.service.JpaRepoFileDataService;
import com.melath.nubecula.storage.service.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class NubeculaApplication {

    public static void main(String[] args) {
        SpringApplication.run(NubeculaApplication.class, args);
    }

    @Bean
    CommandLineRunner init(
            StorageService storageService,
            JpaRepoFileDataService jpaRepoFileDataService,
            UserStorageService userStorageService
    ) {
        return (args) -> {
            jpaRepoFileDataService.setUserStorageService(userStorageService);
            storageService.init();
        };
    }

}
