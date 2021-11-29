package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "book")
@XmlAccessorType(XmlAccessType.FIELD)
public class Book {

    @XmlAttribute
    private String bookName;
    private Author author;

    @XmlAttribute
    private int releaseYear;

    @XmlElementWrapper(name = "genres")
    @XmlElement(name = "genre")
    private String[] genres;

    @XmlAttribute
    private boolean isForOver18;

    public Book() {
    }

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
