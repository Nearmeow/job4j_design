package ru.job4j.io;

import java.io.File;
import java.io.IOException;

public class Dir {

    public static void main(String[] args) throws IOException {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        for (File subfile : file.listFiles()) {
            System.out.println(String.format("name: %s, size: %d", subfile.getName(), subfile.length()));
        }
    }
}
