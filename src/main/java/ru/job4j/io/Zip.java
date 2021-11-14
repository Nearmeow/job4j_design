package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static void packFiles(List<File> sources, File target) {
        Path parentPath = Path.of(target.getParent());
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File sourceFile : sources) {
                Path targetPath = parentPath.relativize(sourceFile.toPath());
                zip.putNextEntry(new ZipEntry(targetPath.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(sourceFile))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);

        String source = argsName.get("d");
        String exclude = argsName.get("e");
        String output = argsName.get("o");
        if (source == null || exclude == null || output == null) {
            throw new IllegalArgumentException("Not enough arguments.");
        }
        Path sourcePath = Path.of(source);
        if (!sourcePath.toFile().isDirectory()) {
            throw new IllegalArgumentException("Source directory doesn't exist.");
        }
        List<Path> listToPack = Search.search(sourcePath, path -> !path.toFile().getName().endsWith(exclude));
        List<File> files = listToPack.stream().map(Path::toFile).collect(Collectors.toList());
        packFiles(files, new File(sourcePath.toFile().getParent() + "/" + output));
    }
}
