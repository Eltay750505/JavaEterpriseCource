package ru.mail.romanov1234567890987.service.util;

import java.util.Random;

public class RandomUtil {
    private Random random = new Random();

    public int getRandomNumber(int max, int min) {
        return random.nextInt(max - min + 1) + min;
    }

    public int getNextInt(int bound) {
        return random.nextInt(bound + 1);
    }
    public int getAnyInt() {
        return random.nextInt();
    }
}
