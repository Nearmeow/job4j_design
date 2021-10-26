package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public String value(String key) {
        return values.get(key);
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            List<String> stringList = read.lines().collect(Collectors.toList());
            for (String line : stringList) {
                if (isValidLine(line)) {
                    String[] array = line.split("=");
                    checkArguments(array);
                    addToValues(array);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isValidLine(String line) {
        return !line.startsWith("#") && !line.isEmpty();
    }

    private void checkArguments(String[] array) {
        if (array[0].isEmpty() || array.length > 2) {
            throw new IllegalArgumentException();
        }
    }

    private void addToValues(String[] array) {
        if (array.length == 1) {
            values.put(array[0].trim(), null);
        } else {
            values.put(array[0].trim(), array[1].trim());
        }
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}
