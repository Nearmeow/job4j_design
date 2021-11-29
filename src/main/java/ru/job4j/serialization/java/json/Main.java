package ru.job4j.serialization.java.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {

    public static void main(String[] args) {
        Author author = new Author("Stephen", "King", "USA");
        String[] bookGenres = new String[]{"thriller", "horror", "mystery"};
        Book book = new Book("Cujo", author, 1981, bookGenres, false);

        final Gson gson = new GsonBuilder().create();
        String jsonBook = gson.toJson(book);
        System.out.println(jsonBook);

        final Book fromJsonBook = gson.fromJson(jsonBook, Book.class);
        System.out.println(fromJsonBook);
    }
}
