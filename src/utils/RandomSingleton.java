package utils;

import java.util.Random;

public class RandomSingleton {
    private static final Random instance = new Random();

    private RandomSingleton() {
        // private constructor to prevent instantiation
    }

    public static Random getInstance() {
        return instance;
    }
}