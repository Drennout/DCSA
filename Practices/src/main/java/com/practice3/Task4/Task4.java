package com.practice3.Task4;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

import static java.nio.file.StandardWatchEventKinds.*;

public class Task4 {
    public static void main(String[] args) throws InterruptedException, IOException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path path = Paths.get("c:\\dir");

        path.register(watchService, ENTRY_CREATE, ENTRY_MODIFY);

        File folder = new File("c:\\dir");


        List<File> files;
        List<FileInMemory> inMemories = new ArrayList<>();

        if (folder.isDirectory()) {
            files = new ArrayList<>(Arrays.asList(Objects.requireNonNull(folder.listFiles())));
        } else {
            files = new ArrayList<>();
        }

        for (File file : files) {
            inMemories.add(new FileInMemory(file, getContent(file), file.getName()));
        }

        boolean poll = true;
        while (poll) {
            WatchKey key = watchService.take();

            for (WatchEvent<?> event : key.pollEvents()) {
                @SuppressWarnings("unchecked")
                WatchEvent<Path> ev = (WatchEvent<Path>) event;
                Path fileName = ev.context();

                if (event.kind().equals(ENTRY_CREATE)) {
                    File file = fileName.toFile();
                    System.out.println(fileName.toUri());
                    if (file.exists()) {
                        inMemories.add(new FileInMemory(file, null, file.getName()));
                        for (FileInMemory f : inMemories) {
                            System.out.println(f.getName());
                        }
                    }
                } else if (event.kind().equals(ENTRY_MODIFY)) {
                    System.out.println("Modified file: " + event.context());
                    File file = fileName.toFile();
                    List<String> old = inMemories
                            .stream()
                            .filter(f -> f.getFile().getName().equals(file.getName()))
                            .findFirst()
                            .get().getContents();

                    FileInMemory fileInMemory = inMemories.stream()
                            .filter(f -> f.getFile().getName().equals(file.getName()))
                            .findFirst().get();

                    inMemories.remove(fileInMemory);
                    fileInMemory.updateContent();
                    inMemories.add(fileInMemory);

                    compareChanges(old, fileInMemory.getContents());
                }

            }
            poll = key.reset();
        }
    }

    public static List<String> getContent(File file) {
        Path p = file.toPath();
        try {
            return Files.readAllLines(p);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void compareChanges(List<String> o, List<String> n) {
        if(o != null) {
            System.out.println("\nDeleted rows");
            for (String content : o) {
                if (!n.contains(content)) {
                    System.out.println(content);
                }
            }

            System.out.println("\n== | | ==\n\nAdded rows");
            for (String content : n) {
                if (!o.contains(content)) {
                    System.out.println(content);
                }
            }
        } else {
            System.out.println("Added row");
            for (String content : n) {
                System.out.println(content);
            }
        }
    }
}

class FileInMemory {
    private List<String> contents;
    private File file;
    private String name;

    FileInMemory(File file, List<String> contents, String name) {
        this.file = file;
        this.contents = contents;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public List<String> getContents() {
        return contents;
    }

    public void setContents(List<String> contents) {
        this.contents = contents;
    }

    public void updateContent() {
        this.contents = Task4.getContent(this.file);
    }
}
