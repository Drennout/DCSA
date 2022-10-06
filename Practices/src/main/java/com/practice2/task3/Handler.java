package com.practice2.task3;

import java.io.File;
import java.util.concurrent.BlockingQueue;

public class Handler implements Runnable {
    private BlockingQueue<File> queue;

    Handler(BlockingQueue<File> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(2000);
                File file = queue.take();
                System.out.println(file.getName() + " is handled");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

