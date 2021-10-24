package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        log.forEach(System.out::println);
    }

    public static List<String> filter(String file) {
        List<String> resultList = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            List<String> strings = in.lines().collect(Collectors.toList());
            resultList = getResultList(strings);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    private static List<String> getResultList(List<String> strings) {
        List<String> resultList = new ArrayList<>();
        for (String elem : strings) {
            String[] arr = elem.split(" ");
            if (arr[arr.length - 2].trim().equals("404")) {
                resultList.add(elem);
            }
        }
        return resultList;
    }
}
