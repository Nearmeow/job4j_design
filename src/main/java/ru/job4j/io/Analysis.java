package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

public class Analysis {

    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            String line;
            boolean isStartTimeNoted = false;
            while ((line = in.readLine()) != null) {
                String[] lineArray = line.split(" ");
                if (lineArray[0].equals("400") || lineArray[0].equals("500")) {
                    if (!isStartTimeNoted) {
                        out.print(String.format("%s;", lineArray[1]));
                        isStartTimeNoted = true;
                    }
                } else {
                    if (isStartTimeNoted) {
                        out.print(String.format("%s;", lineArray[1]));
                        isStartTimeNoted = false;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
