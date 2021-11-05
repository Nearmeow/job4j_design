package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, List<Path>> elementsMap;

    public DuplicatesVisitor() {
        elementsMap = new HashMap<>();
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty currentFile = new FileProperty(file.toFile().length(), file.getFileName().toString());
        if (!elementsMap.containsKey(currentFile)) {
            elementsMap.put(currentFile, new ArrayList<>());
        }
        elementsMap.get(currentFile).add(file.toAbsolutePath());
        return super.visitFile(file, attrs);
    }

    public void printResult() {
        for (Map.Entry<FileProperty, List<Path>> elem : elementsMap.entrySet()) {
            if (elem.getValue().size() > 1) {
                System.out.println("Duplicates found:");
                elem.getValue().forEach(System.out::println);
                System.out.println("----------");
            }
        }
    }
}

