package ru.job4j.io;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SearchFiles extends SimpleFileVisitor<Path> {

    private Predicate<Path> condition;
    private List<Path> resultPaths;

    public SearchFiles(Predicate<Path> condition) {
        this.condition = condition;
        resultPaths = new ArrayList<>();
    }

    public List<Path> getPaths() {
        return resultPaths;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (condition.test(file)) {
            resultPaths.add(file);
        }
        return FileVisitResult.CONTINUE;
    }

}
