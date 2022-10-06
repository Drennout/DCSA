package com.practice2.task2;

import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        try {
            while (true) {
                Callable<Integer> num = new Pow(in.nextInt());
                FutureTask<Integer> task = new FutureTask<>(num);
                Thread t = new Thread(task);
                t.start();

                Thread output = new Thread(new Output(task.get()));
                output.start();
            }
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class Pow implements Callable<Integer> {
    private final int n;

    Pow(int n) {
        this.n = n;
    }

    @Override
    public Integer call() throws Exception {
        Thread.sleep(2000);
        return n * n;
    }
}

class Output implements Runnable {
    private final int n;

    Output(int n) {
        this.n = n;
    }

    @Override
    public void run() {
        System.out.println(n);
    }
}
