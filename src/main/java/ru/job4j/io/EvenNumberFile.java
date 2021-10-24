package ru.job4j.io;

import java.io.FileInputStream;
import java.util.Arrays;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            int[] numArray = convertStringToNum(text.toString());
            checkEvenAndPrintResult(numArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int[] convertStringToNum(String text) {
        return Arrays.stream(text.split(System.lineSeparator())).mapToInt(Integer::parseInt).toArray();
    }

    private static void checkEvenAndPrintResult(int[] numArray) {
        for (int elem : numArray) {
            if (elem % 2 == 0) {
                System.out.println("Число " + elem + " четное");
            } else {
                System.out.println("Число " + elem + " нечетное");
            }
        }
    }
}
