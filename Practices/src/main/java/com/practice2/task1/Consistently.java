package com.practice2.task1;

import java.util.concurrent.ThreadLocalRandom;

public class Consistently {

    public static void main(String[] args) {
        int[] arr = new int[10000];
        for (int i = 0; i < 10000; i++) {
            arr[i] = ThreadLocalRandom.current().nextInt(0, 1000000);
        }
        int max = 0;
        long startTime = System.currentTimeMillis() / 1000;

        try {
            for (int i : arr) {
                max = Math.max(i, max);
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Max: " + max);
        long endTime = System.currentTimeMillis() / 1000;
        System.out.println("time spent: " + (endTime - startTime) + " seconds");
    }
}
