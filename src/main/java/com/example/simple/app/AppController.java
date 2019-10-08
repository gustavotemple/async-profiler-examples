package com.example.simple.app;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping(path = "/v1/api")
public class AppController {

    @GetMapping
    public ResponseEntity get() {
        for (int i = 0; i < 200 + getRandomNumberInRange(0, 10); i++) {
            for (int j = 0; j < 200 + getRandomNumberInRange(0, 10); j++) {

            }
        }

        return ResponseEntity.ok().build();
    }

    private static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random random = new Random();
        return random.ints(min, (max + 1)).findFirst().getAsInt();
    }

}
