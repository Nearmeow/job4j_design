package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("C:/test/test.txt")) {
            for (int i = 1; i < 11; i++) {
                for (int j = 1; j < 11; j++) {
                    out.write(String.format("%4d", i * j).getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
