package ru.job4j.serialization.json;

public class Author {

    private String name;
    private String surname;
    private String country;

    public Author(String name, String surname, String country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }

    @Override
    public String toString() {
        return "Author{" + "name=" + name + ", surname=" + surname + ", country=" + country + "}";
    }
}
