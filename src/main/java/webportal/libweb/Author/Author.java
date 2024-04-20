package webportal.libweb.Author;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import webportal.libweb.Book.Book;

@Table(name = "Authors")
@Entity
@Data
@SequenceGenerator(name = "author_gen", sequenceName = "author_seq", allocationSize = 1, initialValue = 1)
public class Author {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_gen")
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Book> books = new ArrayList<>();

    public Author(){}

    public Author(String name){
        this.fullName = name;
    }

    public Author(String name, List<Book> books){
        this.fullName = name;
        this.books = books;
    }

    public String getShortName(){
        String[] fullNameParts = this.fullName.split(" ");

        StringBuffer shortName = new StringBuffer(this.fullName.length());

        shortName.append(fullNameParts[0]);
        shortName.append(' ');
        for (int index = 1; index < fullNameParts.length; index++) {
            shortName.append(fullNameParts[index].charAt(0));
            shortName.append('.');
        }

        return shortName.toString();
    }
}