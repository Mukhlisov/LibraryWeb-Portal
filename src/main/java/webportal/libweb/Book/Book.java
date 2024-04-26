package webportal.libweb.Book;

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
import webportal.libweb.Author.Author;


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

    public Book(){}

    public Book(String title, Integer quantity, Integer year, String cover, Set<Author> authors){
        this.title = title;
        this.quantity = quantity;
        this.year = year;
        this.cover = cover;
        this.authors = authors;
    }

    public String getAllAuthors(){
        StringBuffer buffer = new StringBuffer(40*authors.size());
        for (Author author : this.authors) {
            buffer.append(author.getFullName());
            buffer.append("; ");
        }
        return buffer.toString();
    }
}
