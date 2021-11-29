package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "author")
public class Author {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private String surname;

    @XmlAttribute
    private String country;

    public Author() {
    }

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
