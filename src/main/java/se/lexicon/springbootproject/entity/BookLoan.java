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
// book loan id
    @Id
    @GeneratedValue
    private int id;

// loan date
    @Column
    @Setter
    private LocalDate loanDate;

// due date
    @Column
    @Setter
    private LocalDate dueDate;

// returned
    @Column
    @Setter
    private boolean returned;

//borrower
    @JoinColumn (name = "appuser_id")
    @ManyToOne (cascade = CascadeType.PERSIST)
    @Setter
    private AppUser borrower;


//book associeted with loan
    @JoinColumn (name = "book_id")
    @ManyToOne (cascade = CascadeType.PERSIST)
    @Setter
    private Book book;


//constructor to set loan date, due date, returned, borrower and book
    public BookLoan(LocalDate loanDate, LocalDate dueDate, boolean returned, AppUser borrower, Book book) {
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returned = returned;
        this.borrower = borrower;
        this.book = book;
    }



}
