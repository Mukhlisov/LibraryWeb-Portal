package webportal.libweb.DTOs;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookAddDTO {
    private Long id;
    private String title;
    private int year;
    private int quantity;
    private String authors;
}
