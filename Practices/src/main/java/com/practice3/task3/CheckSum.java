package com.practice3.task3;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class CheckSum {

    // Compute a 16-bit checksum for all the remaining bytes
    // in the given byte buffer

    private static int sum(ByteBuffer bb) {
        int sum = 0;
        while (bb.hasRemaining()) {
            if ((sum & 1) != 0)
                sum = (sum >> 1) + 0x8000;
            else
                sum >>= 1;
            sum += bb.get() & 0xff;
            sum &= 0xffff;
        }
        return sum;
    }

    // Compute and print a checksum for the given file

    public static int sum(File f) throws IOException {

        FileInputStream fis = new FileInputStream(f);
        // Open the file and then get a channel from the stream
        try (fis; FileChannel fc = fis.getChannel()) {

            // Get the file's size and then map it into memory
            int sz = (int) fc.size();
            MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, sz);

            return sum(bb);
        }
    }

    public static int size(File f) throws IOException {
        FileInputStream fis = new FileInputStream(f);
        try (fis; FileChannel fc = fis.getChannel()) {
            return ((int) fc.size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {


        File f = new File("file.txt");
        try {
            FileInputStream fis = new FileInputStream(f);
            FileChannel fc = fis.getChannel();

            System.out.println("Checksum: " + sum(f) + "\nSize: " + size(f) + "\nFile name: " + f);
        } catch (IOException e) {
            System.err.println(f + ": " + e);
        }
    }
}