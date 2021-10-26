package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        save(log, "result.txt");
    }

    public static List<String> filter(String file) {
        List<String> resultList = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String line = in.readLine();
            while (line != null) {
                String[] arr = line.split(" ");
                if (arr[arr.length - 2].trim().equals("404")) {
                    resultList.add(line);
                }
                line = in.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)))) {
            log.forEach(out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
