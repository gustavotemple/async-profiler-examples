package com.example.simple.app;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping(path = "/v1")
public class AppController {

    private static final int LOOP = 200;
    private static final int MIN = 0;
    private static final int MAX = 2;

    @GetMapping("/nothing")
    public ResponseEntity nothing() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/sleep")
    public ResponseEntity sleep() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/random1")
    public ResponseEntity random1() {
        for (int i = 0; i < LOOP + getRandomNumberInRange1(MIN, MAX); i++)
            for (int j = 0; j < LOOP + getRandomNumberInRange1(MIN, MAX); j++) {
            }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/random2")
    public ResponseEntity random2() {
        for (int i = 0; i < LOOP + getRandomNumberInRange2(MIN, MAX); i++)
            for (int j = 0; j < LOOP + getRandomNumberInRange2(MIN, MAX); j++) {
            }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/random3")
    public ResponseEntity random3() {
        for (int i = 0; i < LOOP + getRandomNumberInRange3(MIN, MAX); i++)
            for (int j = 0; j < LOOP + getRandomNumberInRange3(MIN, MAX); j++) {
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
