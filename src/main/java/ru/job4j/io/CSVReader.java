package ru.job4j.io;

import java.io.*;
import java.util.*;

public class CSVReader {

    public static void handle(ArgsName argsName) {
        String source = (argsName.get("path"));
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String[] filters = argsName.get("filter").split(",");

        try {
            validateArgs(source, delimiter, out, filters);
            List<List<String>> values = getValues(source, delimiter);
            List<String> resultValues = getValuesByFilters(values, filters);
            resultOut(resultValues, out);
        } catch (IllegalArgumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void validateArgs(String source, String delimiter, String target, String[] filters) {
        if (source == null || delimiter == null || target == null || filters == null) {
            throw new IllegalArgumentException("Not enough arguments.");
        }
        if (!new File(source).isFile()) {
            throw new IllegalArgumentException("Wrong source file path.");
        }
        if (!"stdout".equals(target) && !target.endsWith(".csv")) {
            throw new IllegalArgumentException("Wrong format of 'out' argument.");
        }
    }

    private static List<List<String>> getValues(String source, String delimiter) throws FileNotFoundException {
        List<List<String>> values = new ArrayList<>();
        try (Scanner scanner = new Scanner(new BufferedInputStream(new FileInputStream(source))).useDelimiter(System.lineSeparator())) {
            while (scanner.hasNext()) {
                values.add(Arrays.asList(scanner.next().split(delimiter)));
            }
        }
        return values;
    }

    private static List<String> getValuesByFilters(List<List<String>> values, String[] filters) {
        List<String> resultValues = new ArrayList<>();
        List<Integer> indexes = getIndexesByFilters(filters, values.get(0));
        for (List<String> value : values) {
            StringJoiner stringJoiner = new StringJoiner(";");
            for (Integer idx : indexes) {
                stringJoiner.add(value.get(idx));
            }
            resultValues.add(stringJoiner.toString());
        }
        return resultValues;
    }

    private static List<Integer> getIndexesByFilters(String[] filters, List<String> headersList) {
        List<Integer> indexes = new ArrayList<>();
        for (String filter : filters) {
            indexes.add(headersList.indexOf(filter));
        }
        return indexes;
    }

    private static void resultOut(List<String> resultList, String out) throws FileNotFoundException {
        if ("stdout".equals(out)) {
            resultList.forEach(System.out::println);
        } else {
            try (PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(out)))) {
                resultList.forEach(writer::println);
            }
        }
    }

    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}
