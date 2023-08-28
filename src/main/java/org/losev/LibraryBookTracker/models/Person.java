package org.losev.LibraryBookTracker.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name can't be empty")
    @Size(min = 2, max = 100, message = "Name should between 2 and 100 char")
    @Column(name = "name")
    private String name;

    @Column(name = "birth_year")
    @Min(value = 1900, message = "Year should be greater then 1900")
    private int birth_year;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    public Person(String name, int birth_year, List<Book> books) {
        this.name = name;
        this.birth_year = birth_year;
        this.books = books;
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(int birth_year) {
        this.birth_year = birth_year;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
