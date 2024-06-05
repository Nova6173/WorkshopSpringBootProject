package se.lexicon.springbootproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.lexicon.springbootproject.entity.Author;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository <Author, String> {

    List<Author> findAuthorsByFirstNameAndLastNameContainingIgnoreCase(String firstName, String lastName);
    List<Author>findAuthorsByFirstName(String firstName);
    List<Author>findAuthorsByLastName(String lastName);

    @Query("select a from Author a join Book b where b.id = :bookId")
    List<Author>findAuthorsByBookId(int bookId, String name);

    @Modifying
    @Query("update Author a set a.firstName = :newFirstName, a.lastName = :newLastName where a.firstName = :firstName and a.lastName = :lastName")
    void updateAuthorByFirstNameAndLastName(@Param("authorId") int id,String firstName, String lastName, String newFirstName, String newLastName);
    @Modifying
    @Query("delete from Author a where a.firstName = :firstName and a.lastName = :lastName")
    void deleteAuthorByFirstNameAndLastName(@Param("authorId") int id, String firstName, String lastName);




}
