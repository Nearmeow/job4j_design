package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Specify arguments.");
        }
        for (String elem : args) {
            String[] pair = elem.split("=");
            if (pair.length != 2 || !isValidKey(pair[0].trim()) || pair[1].trim().isEmpty()) {
                throw new IllegalArgumentException("Invalid argument format. Required format - '-key=value'.");
            }
            values.put(pair[0].substring(1), pair[1]);
        }
    }

    private boolean isValidKey(String key) {
        return key.startsWith("-") && key.length() > 1;
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
