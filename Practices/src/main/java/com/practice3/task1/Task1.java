package main.java.com.practice3.task1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Task1 {
    public static void main(String[] args) {
        Path path = Paths.get("task1.txt");

        try {
            List<String> contents = Files.readAllLines(path);
            for (String content : contents) {
                System.out.println(content);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
