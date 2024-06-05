package se.lexicon.springbootproject.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.lexicon.springbootproject.entity.Book;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    Book findByIsbnIgnoreCase(String isbn);

    Book findByTitleContains(String title);

    List<Book> findByMaxLoanDaysLessThan(int maxLoanDays);
    @Query ("select b from Book b join Book Loan l where l.returned = :false")
    List<Book> findByCurrentlyOnLoan(@Param("bookLoanId") int bookLoanId);

    @Query ("select b from Book b join BookLoan l where l.returned = :false and l.dueDate between :date1 and :date2")
    List<Book> findBookByLoansBetween(@Param("date1") LocalDate date1 , @Param("date2") LocalDate date2);


    @Query ("select b from Book b join BookLoan l where l.returned = :false and l.dueDate <CURRENT_DATE")
    Book findOverDueBooks(@Param("bookLoanId") int bookLoanId);



}
