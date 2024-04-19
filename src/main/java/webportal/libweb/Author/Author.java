package webportal.libweb.Author;

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
import webportal.libweb.Book.Book;

@Table(name = "Authors")
@Entity
@Data
@SequenceGenerator(name = "author_gen", sequenceName = "author_seq", allocationSize = 1)
public class Author {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_gen")
    private Long id;

    @Column(nullable = false)
    private String name;
    
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Book> books;

    public Author(){}

    public Author(String name){
        this.name = name;
    }
}