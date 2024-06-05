package se.lexicon.springbootproject.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString


@Entity
public class Author  {
// Id of author
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (updatable = false)
    private int id;

// First name of author
   @Column (nullable = false)
    @Setter
    private String firstName;

// Last name of author

   @Column (nullable = false)
   @Setter
   private String lastName;



    @ManyToMany(mappedBy = "book")
    @JoinColumn(name = "book_id")
    private Set<Book> writtenBooks = new HashSet<>();

    //Constructor to create a new author
    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


//List of books written by author
    @OneToMany (mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> books;


//Methods to add and remove books
    public void addBook(Book book) {
        this.books.add(book);
    }

    public void removeBook(Book book) {
        this.books.remove(book);
    }
}
