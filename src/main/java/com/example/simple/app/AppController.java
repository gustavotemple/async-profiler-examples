package com.example.simple.app;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping(path = "/v1/api")
public class AppController {

    @GetMapping
    public ResponseEntity get() {
        for (int i = 0; i < 200 + getRandomNumberInRange1(0, 2); i++) {
            for (int j = 0; j < 200 + getRandomNumberInRange1(0, 2); j++) {
            }
        }

        return ResponseEntity.ok().build();
    }

    private static int getRandomNumberInRange1(int min, int max) {
        if (min >= max || (max - min + 1 > Integer.MAX_VALUE)) {
            throw new IllegalArgumentException("Invalid range");
        }

        double rand = Math.random();
        return (int) (rand * ((max - min) + 1)) + min;
    }

    private static int getRandomNumberInRange2(int min, int max) {
        if (min >= max || (max - min + 1 > Integer.MAX_VALUE)) {
            throw new IllegalArgumentException("Invalid range");
        }

        Random random = new Random();
        return random.ints(min, (max + 1)).findFirst().getAsInt();
    }

    private static int getRandomNumberInRange3(int min, int max) {
        if (min >= max || (max - min + 1 > Integer.MAX_VALUE)) {
            throw new IllegalArgumentException("Invalid range");
        }

        return ThreadLocalRandom.current()
                .nextInt(min, max + 1);
    }

}
