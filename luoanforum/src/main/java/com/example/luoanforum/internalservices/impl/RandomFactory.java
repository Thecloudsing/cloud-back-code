package com.example.luoanforum.internalservices.impl;

import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author 落扶苏
 * @version 1.1
 */
@Service
public class RandomFactory {
    private final Random random = new Random();

    public Random getRandom() {
        return random;
    }
}
