package com.github.mukhlisov;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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

    private Long book_id;

    private Long user_id;

    @Temporal(TemporalType.DATE)
    private LocalDate rent_start_date;

    @Temporal(TemporalType.DATE)
    private LocalDate rent_end_date;
}
