package com.practice2.task1;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadLocalRandom;

public class FORIGOR {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int[] arr = new int[10];
        for (int i = 0; i < 10; i++){
            arr[i] = ThreadLocalRandom.current().nextInt(0, 50);
        }

        Callable<Integer> p1 = new Potok(Arrays.copyOfRange(arr, 0, 5));
        Callable <Integer> p2 = new Potok(Arrays.copyOfRange(arr, 5, 10));

        FutureTask<Integer> f1 = new FutureTask<>(p1);
        FutureTask<Integer> f2 = new FutureTask<>(p2);

        Thread t1 = new Thread(f1);
        Thread t2 = new Thread(f2);
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(f1.get());
        System.out.println(f2.get());
        System.out.println(Math.max(f1.get(), f2.get()));
    }
}

class Potok implements Callable<Integer> {
    private int[] arr;

    Potok (int[] arr) {
        this.arr = arr;
    }

    @Override
    public Integer call() {
        int max = 0;
        for (int i : arr) {
            max = Math.max(max, i);
        }
        return max;
    }
}
