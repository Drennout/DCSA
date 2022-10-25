package com.practice3.task2;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

public class Task2 {
    public static void copyFileUsingStream(File source, File dest) {

        try (FileInputStream fin = new FileInputStream(source)) {
            FileOutputStream fout = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int r;

            do {
                r = fin.read(buf, 0, buf.length);
                if (r > 0) {
                    fout.write(buf, 0, r);
                }
            } while (r > 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void copyFileUsingChannels(File source, File dest) throws IOException {
        FileChannel sourceChannel = null;
        FileChannel destChannel = null;
        try {
            sourceChannel = new FileInputStream(source).getChannel();
            destChannel = new FileOutputStream(dest).getChannel();
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            assert sourceChannel != null;
            sourceChannel.close();
            assert destChannel != null;
            destChannel.close();
        }
    }

    public static void copyFileUsingApacheCommon(File source, File dest) throws IOException {
        FileUtils.copyFile(source, dest);
    }

    public static void copyUsingFiles(File source, File dest) throws IOException {
        Files.copy(source.toPath(), dest.toPath());
    }

    public static void main(String[] args) throws IOException {
        File source = new File("task1.txt");
        File destIO = new File("copy_by_io_file.txt");
        File destNIO = new File("copy_by_nio_file.txt");
        File destApache = new File("copy_by_apache_file.txt");
        File destFiles = new File("copy_by_files_file.txt");


        //copy file conventional way using Stream
        long start = System.currentTimeMillis();
        copyFileUsingStream(source, destIO);
        System.out.println("Time taken by Stream Copy = "+(System.currentTimeMillis()-start));

        //copy files using java.nio FileChannel
        start = System.currentTimeMillis();
        copyFileUsingChannels(source, destNIO);
        System.out.println("Time taken by Channel Copy = "+(System.currentTimeMillis()-start));

        //copy files using apache commons io
        start = System.currentTimeMillis();
        copyFileUsingApacheCommon(source, destApache);
        System.out.println("Time taken by Apache Commons IO Copy = "+(System.currentTimeMillis()-start));

        //using Java 7 Files class
        start = System.currentTimeMillis();
        copyUsingFiles(source, destFiles);
        System.out.println("Time taken by Java7 Files Copy = "+(System.currentTimeMillis()-start));

        destIO.deleteOnExit();
        destNIO.deleteOnExit();
        destFiles.deleteOnExit();
        destApache.deleteOnExit();
    }
}
