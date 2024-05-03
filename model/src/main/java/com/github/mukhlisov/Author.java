package com.github.mukhlisov;

import java.util.ArrayList;
import java.util.List;

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

@Table(name = "Authors")
@Entity
@Data
@SequenceGenerator(name = "author_gen", sequenceName = "author_seq", allocationSize = 1)
public class Author {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_gen")
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Book> books = new ArrayList<>();

    public Author(){}

    public Author(Long id, String fullName){
        this.id = id;
        this.fullName = fullName;
    }

    public Author(String fullName){
        this.fullName = fullName;
    }

    public Author(String name, List<Book> books){
        this.fullName = name;
        this.books = books;
    }

    public String getShortName(){
        String[] fullNameParts = this.fullName.split(" ");

        StringBuilder shortName = new StringBuilder(this.fullName.length());

        shortName.append(fullNameParts[0]);
        shortName.append(' ');
        for (int index = 1; index < fullNameParts.length; index++) {
            shortName.append(fullNameParts[index].charAt(0));
            shortName.append('.');
        }

        return shortName.toString();
    }
}