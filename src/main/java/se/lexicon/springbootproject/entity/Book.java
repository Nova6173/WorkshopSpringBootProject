package se.lexicon.springbootproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

@Entity
public class Book {
// Id of the book
    @Id
    @GeneratedValue
    private int id;

//ISBN
    @Column (nullable = false, length = 13)
    @Setter
    private String isbn;

//Book title
    @Column (nullable = false)
    @Setter
    private String Title;


//set authors associated with book
    @ManyToMany
    @JoinTable(name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    @Setter private Set<Author> authors;

// maximum days a book can be loaned
    @Column (nullable = false)
    private int maxLoanDays;


//List of book loans
    @OneToMany (mappedBy = "book")
    @Setter
    private List<BookLoan> loans ;


// Book constructor
    public Book(String isbn, String title, int maxLoanDays) {
        this.isbn = isbn;
        Title = title;
        this.maxLoanDays = maxLoanDays;
    }

// set author
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    @Setter
    private Author author;

// add and remove author
    public void addAuthor(Author author) {
        this.author = author;
    }
          public void removeAuthor(){
        this.author = null;

    }

// calculate due date
    private LocalDate dueDate;

    //book availability
    private boolean available;

//set due date maximum loan days
    public void calculateDueDate() {
        dueDate = LocalDate.now().plusDays(maxLoanDays);
    }

    //set availability
    public void setAvailable(boolean available) {
        this.available = available;
    }
}
