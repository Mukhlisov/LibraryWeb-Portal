package webportal.libweb.RentedBook;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import webportal.libweb.Book.Book;
import webportal.libweb.User.User;

@Entity
@Data
@Table(name = "Rented_books")
public class RentedBook {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Temporal(TemporalType.DATE)
    private LocalDate rent_end_date;
}
