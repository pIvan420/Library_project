package ru.pivan.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.*;

public class Person {
    private int id;

    //Фамилия Имя Отчество
    @NotEmpty(message = "Имя не может быть пустым")
    //@Pattern()
    // todo прописать паттерн + написать аннотацию уникальности
    private String full_name;

    @Min(value = 1910, message = "Год не может быть меньше 1910")
    @Max(value = 2023, message = "Год не может быть больше 2023")
    private int birth_year;

    public Person(int id, String full_name, int birth_year) {
        this.id = id;
        this.full_name = full_name;
        this.birth_year = birth_year;
    }

    public Person() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public int getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(int birth_year) {
        this.birth_year = birth_year;
    }
}
