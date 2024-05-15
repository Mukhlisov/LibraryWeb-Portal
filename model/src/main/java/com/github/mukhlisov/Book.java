package com.github.mukhlisov;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Books")
@SequenceGenerator(name = "book_gen", sequenceName = "book_sequence", allocationSize = 1, initialValue = 1)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_gen")
    private Long id;

    @Column(nullable = false)
    private String title;

    private Integer quantity;

    private Integer year;
    private String cover;

    @ManyToMany(mappedBy = "books", fetch = FetchType.EAGER)
    private Set<Author> authors;

    @ManyToMany
    private List<User> users;

    public Book(){}

    public Book(Long id, String title, Integer quantity, Integer year, String cover) {
        this.id = id;
        this.title = title;
        this.quantity = quantity;
        this.year = year;
        this.cover = cover;
    }

    public Book(String title, Integer quantity, Integer year, String cover, Set<Author> authors){
        this.title = title;
        this.quantity = quantity;
        this.year = year;
        this.cover = cover;
        this.authors = authors;
    }

    public Book(String title, Integer quantity, Integer year, String cover) {
        this.title = title;
        this.quantity = quantity;
        this.year = year;
        this.cover = cover;
    }

    public String getAllAuthors(){
        StringBuilder authorList = new StringBuilder(40*authors.size());
        for (Author author : this.authors) {
            authorList.append(author.getFullName());
            authorList.append("; ");
        }
        return authorList.toString();
    }
}
