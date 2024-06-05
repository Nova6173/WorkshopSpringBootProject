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
    // ID of user
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private int id;


    // Username of user
    @Column(nullable = false,unique = true)
    @Setter private String username;

    // Password of user
    @Column(nullable = false)
    @Setter private String password;

    // Regdate of user
    @Column
    @Setter private LocalDate regDate;

    // User deatils
    @Setter
    @OneToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "details_id")
    private Details userDetails;


    //List of books
    @Setter
    @ManyToMany
    @JoinTable(name = "appuser_book",
            joinColumns = @JoinColumn(name = "appuser_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books;


    // List of books loans
    @OneToMany(mappedBy = "borrower")
    private List<BookLoan> loans;


    // list of books on loan to user
    @OneToMany (mappedBy = "borrower")
    private List<Book> bookLoans;


    // create a new user with username and password
    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;
        this.regDate = LocalDate.now();

    }
    // add a book loan to user
    public void addBookLoan(BookLoan bookLoan) {
        bookLoan.setBorrower(this);
        loans.add(bookLoan);
    }


  // Method to add a book loan to the user, ensuring the book is available
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
