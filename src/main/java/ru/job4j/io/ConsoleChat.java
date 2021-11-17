package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConsoleChat {

    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> chatLog = new ArrayList<>();
        List<String> botAnswers = readPhrases();
        Random random = new Random();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String phrase = reader.readLine();
            while (!phrase.equals(OUT)) {
                if (phrase.equals(STOP)) {
                    chatLog.add(phrase);
                    phrase = reader.readLine();
                    while (!phrase.equals(CONTINUE)) {
                        chatLog.add(phrase);
                        phrase = reader.readLine();
                    }
                }
                chatLog.add(phrase);
                String nextBotAnswer = botAnswers.get(random.nextInt(botAnswers.size()));
                System.out.println(nextBotAnswer);
                chatLog.add(nextBotAnswer);
                phrase = reader.readLine();
            }
            chatLog.add(phrase);
            saveLog(chatLog);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers))) {
            br.lines().forEach(phrases::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("C:/test/chat_log.txt", "C:/test/bot_answers.txt");
        cc.run();
    }
}
