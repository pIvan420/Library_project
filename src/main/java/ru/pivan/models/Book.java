package ru.pivan.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {

    private int id;

    private int person_id;

    @NotEmpty(message = "Название не может быть пустым")
    private String name;

    @Size(min = 1, max = 200, message = "Имя автора может включать до 200 символов")
    private String author;

    @Min(value = 867, message = "Год не может быть меньше 867")
    @Max(value = 2023, message = "Год не может быть больше 2023")
    private int publish_year;

    public Book(int id, int person_id, String name, String author, int publish_year) {
        this.id = id;
        this.person_id = person_id;
        this.name = name;
        this.author = author;
        this.publish_year = publish_year;
    }

    public Book() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublish_year() {
        return publish_year;
    }

    public void setPublish_year(int publish_year) {
        this.publish_year = publish_year;
    }
}
