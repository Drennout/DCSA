package com.practice2.task1;

import java.util.*;
import java.util.concurrent.*;

public class MThreads {
    private final static int SIZE = 3;

    public static void main(String[] args) {
        int[] arr = new int[10000];

        for (int i = 0; i < 10000; i++) {
            arr[i] = ThreadLocalRandom.current().nextInt(0, 1000000);
        }
        ExecutorService service = Executors.newFixedThreadPool(SIZE);
        List<Future<Integer>> futures = new ArrayList<>();

        long start = System.currentTimeMillis()/1000;
        for (int i = 0; i < SIZE; i ++) {
            final int finalI = i;

            final Future<Integer> init = service.submit(() -> {
                int[] subArr = Arrays.copyOfRange(arr, (finalI*10000/SIZE), (finalI + 1)*10000/SIZE);

                int max = 0;
                try {
                    for (int j : subArr) {
                        if (j > max)
                            max = j;
                        Thread.sleep(1);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return max;
            });

            futures.add(init);
        }

        int max = futures.stream().mapToInt(n -> {
            try {
                return n.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return 0;
        }).max().orElseThrow(NoSuchElementException::new);

        System.out.println("Max: " + max);
        System.out.println("time spent: " + (System.currentTimeMillis()/1000 - start) + " sec");
        System.out.println("memory spent: " + ((Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory())/1048576) + "M byte");
        service.shutdown();
    }
}
