package webportal.libweb.DTOs;


import lombok.Setter;

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

    public String getAuthors(){
        return this.authors;
    }
}
