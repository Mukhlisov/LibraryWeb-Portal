package webportal.libweb.DTOs;

import java.util.ArrayList;
import java.util.List;

import lombok.Setter;
import webportal.libweb.Author.Author;

@Setter
public class BookAddDTO {
    private String title;
    private int year;
    private int quantity;
    private String authors;

    public String getTitle(){
        return this.title;
    }

    public int getYear(){
        return this.year;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public List<Author> getAuthors(){
        System.out.println(this.quantity);
        List<Author> list = new ArrayList<>();
        for (String authorName : this.authors.split(";")) {
            list.add(new Author(authorName.trim()));
        }
        return list;
    }
}
