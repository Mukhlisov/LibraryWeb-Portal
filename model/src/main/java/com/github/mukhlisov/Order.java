package com.github.mukhlisov;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;


@Data
@Table(name = "Orders")
@Entity
@SequenceGenerator(name = "order_gen", sequenceName = "order_seq", allocationSize = 1)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_gen")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Temporal(TemporalType.DATE)
    private LocalDate rent_start_date;

    @Temporal(TemporalType.DATE)
    private LocalDate rent_end_date;

    public Order(){}

    public Order(Book book, User user, LocalDate rent_start_date) {
        this.book = book;
        this.user = user;
        this.rent_start_date = rent_start_date;
    }

    public String convertToNormalDate(LocalDate date){
        if (date == null){
            return "";
        }
        String[] ymd = date.toString().split("-");
        return "%s.%s.%s".formatted(ymd[2], ymd[1], ymd[0]);
    }
}
