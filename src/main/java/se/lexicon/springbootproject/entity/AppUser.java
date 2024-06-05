package se.lexicon.springbootproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private int id;

    @Column(nullable = false,unique = true)
    @Setter private String username;

    @Column(nullable = false)
    @Setter private String password;

    @Column
    @Setter private LocalDate regDate;

    @Setter
    @OneToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "details_id")
    private Details userDetails;

    @Setter
    @ManyToMany
    @JoinTable(name = "appuser_book",
            joinColumns = @JoinColumn(name = "appuser_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books;

    @OneToMany(mappedBy = "borrower")
    private List<BookLoan> loans;

    @OneToMany (mappedBy = "borrower")
    private List<Book> bookLoans;

    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;
        this.regDate = LocalDate.now();

    }

    public void addBookLoan(BookLoan bookLoan) {
        bookLoan.setBorrower(this);
        loans.add(bookLoan);
    }



    public void addBookLoan(Book book) {
        if (!book.isAvailable()) {
            throw new IllegalStateException("Book is not available for loan.");
        }

        BookLoan bookLoan = new BookLoan();
        bookLoan.setBook(book);
        bookLoan.setBorrower(this);

        loans.add(bookLoan);

        book.setAvailable(false);
    }

    }
