package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonObjectMain {

    public static void main(String[] args) {
        Author author = new Author("Stephen", "King", "USA");
        String[] bookGenres = new String[]{"thriller", "horror", "mystery"};
        Book book = new Book("Cujo", author, 1981, bookGenres, false);

        JSONObject jsonAuthor = new JSONObject();
        jsonAuthor.put("name", author.getName());
        jsonAuthor.put("surname", author.getSurname());
        jsonAuthor.put("country", author.getCountry());

        List<String> genresList = new ArrayList<>();
        genresList.add("thriller");
        genresList.add("horror");
        genresList.add("mystery");
        JSONArray jsonGenres = new JSONArray(genresList);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("bookName", book.getBookName());
        jsonObject.put("releaseYear", book.getReleaseYear());
        jsonObject.put("isForOver18", book.isForOver18());
        jsonObject.put("author", jsonAuthor);
        jsonObject.put("genres", jsonGenres);

        System.out.println(jsonObject.toString());
        System.out.println(new JSONObject(book).toString());
    }
}
