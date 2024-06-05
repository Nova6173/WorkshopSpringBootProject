package se.lexicon.springbootproject.entity;

import jakarta.persistence.*;
import lombok.*;

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

    @Id
    @GeneratedValue
    private int id;


    @Column (nullable = false, length = 13)
    @Setter
    private String isbn;


    @Column (nullable = false)
    @Setter
    private String Title;

    @ManyToMany
    @JoinTable(name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    @Setter private Set<Author> authors;


    @Column (nullable = false)
    private int maxLoanDays;

    @OneToMany (mappedBy = "book")
    @Setter
    private List<BookLoan> loans ;

    public Book(String isbn, String title, int maxLoanDays) {
        this.isbn = isbn;
        Title = title;
        this.maxLoanDays = maxLoanDays;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    @Setter
    private Author author;

    public void addAuthor(Author author) {
        this.author = author;
    }
          public void removeAuthor(){
        this.author = null;

    }
}
