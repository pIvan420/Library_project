package ru.pivan.models;

public class Book {

    //todo прописать валидацию
    private int id;

    private int person_id;

    private String name;

    private String author;

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
