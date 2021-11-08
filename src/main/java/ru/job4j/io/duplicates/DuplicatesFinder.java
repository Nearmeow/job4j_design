package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DuplicatesFinder {

    public static void main(String[] args) throws IOException {
        DuplicatesVisitor visitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("C:/projects/job4j_design"), visitor);
        List<List<Path>> duplicates = visitor.getResultList();
        if (duplicates.size() == 0) {
            System.out.println("No duplicates found.");
        } else {
            for (List<Path> elem : duplicates) {
                System.out.println("Duplicates found:");
                elem.forEach(System.out::println);
                System.out.println("----------");
            }
        }
    }
}