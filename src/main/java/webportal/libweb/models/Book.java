package webportal.libweb.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import webportal.libweb.models.enums.Status;

@Data
@Entity
@Table(name = "Books")
@SequenceGenerator(name = "book_gen", sequenceName = "book_sequence", allocationSize = 1)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_gen")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

   /*  @Transient
    private String uniqueLink;
 */
    /* public String getUniqueLink(){
        return String.format("%s-%d", this.title, this.id);
    } */

}
