package ru.job4j.serialization.json;

import java.util.Arrays;

public class Book {

    private String bookName;
    private Author author;
    private int releaseYear;
    private String[] genres;
    private boolean isForOver18;

    public Book(String bookName, Author author, int releaseYear, String[] genres, boolean isForOver18) {
        this.bookName = bookName;
        this.author = author;
        this.releaseYear = releaseYear;
        this.genres = genres;
        this.isForOver18 = isForOver18;
    }

    @Override
    public String toString() {
        return "Book{" + "bookName=" + bookName + ", author=" + author + ", releaseYear=" + releaseYear + ", genres=" + Arrays.toString(genres) + ", isForOver18=" + isForOver18 + "}";
    }
}
