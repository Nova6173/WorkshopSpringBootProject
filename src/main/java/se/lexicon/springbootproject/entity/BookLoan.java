package se.lexicon.springbootproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

@Entity
public class BookLoan {

    @Id
    @GeneratedValue
    private int id;

    @Column
    @Setter
    private LocalDate loanDate;


    @Column
    @Setter
    private LocalDate dueDate;


    @Column
    @Setter
    private boolean returned;

    @JoinColumn (name = "appuser_id")
    @ManyToOne (cascade = CascadeType.PERSIST)
    @Setter
    private AppUser borrower;

    @JoinColumn (name = "book_id")
    @ManyToOne (cascade = CascadeType.PERSIST)
    @Setter
    private Book book;



    public BookLoan(LocalDate loanDate, LocalDate dueDate, boolean returned, AppUser borrower, Book book) {
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returned = returned;
        this.borrower = borrower;
        this.book = book;
    }



}
