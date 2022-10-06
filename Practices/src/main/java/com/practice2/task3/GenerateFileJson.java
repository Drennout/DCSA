package com.practice2.task3;

import java.io.File;
import java.util.concurrent.BlockingQueue;

public class GenerateFileJson implements Runnable {
    private File file;
    private String name;
    private BlockingQueue<File> queue;

    GenerateFileJson(BlockingQueue<File> queue) {
        this.queue = queue;
    }

    GenerateFileJson(String name) {
        this.name = name;
        this.file = new File(this.name);
    }

    public File getFile() {
        return file;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5000; i++) {
                GenerateFileJson generateFileJson = new GenerateFileJson("file" + i);
                queue.put(generateFileJson.getFile());
                System.out.println("File " + i + " is created and pushed in queue");
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}