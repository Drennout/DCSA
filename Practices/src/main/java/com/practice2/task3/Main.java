package com.practice2.task3;

import java.io.File;
import java.util.concurrent.*;

public class Main {
    private final BlockingQueue<File> queue = new ArrayBlockingQueue<>(5, true);

    public static void main(String[] args) {
        Main main = new Main();

        Thread h = new Thread(new Handler(main.queue));
        Thread t = new Thread(new GenerateFileJson(main.queue));
        h.start();
        t.start();
    }
}
