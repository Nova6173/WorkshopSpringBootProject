package se.lexicon.springbootproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import se.lexicon.springbootproject.entity.BookLoan;

import java.time.LocalDate;
import java.util.List;

public interface BookLoanRepository extends JpaRepository<BookLoan, String> {
    @Query ("select l from BookLoan l join AppUser a where a.id = :borrowerId")
    List<BookLoan> findBookLoansByBorrowerId(@Param("borrowerId") int borrowerId);

    List<BookLoan> findBookLoansByBookId(int bookId);

    @Query ("select l from BookLoan l where l.returned = false")
    List<BookLoan> findBookLoansNotYetReturned();

    @Query
    ("select l from BookLoan l where l.returned = false and l.dueDate < CURRENT_DATE")
    List<BookLoan> findOverDueBookLoans();

    @Query ("select l from BookLoan l where l.loanDate between :date1 and :date2")
    List<BookLoan> findBookLoansBetweenDates(@Param("date1") LocalDate date1, @Param("date2") LocalDate date2);


    @Modifying
    @Query ("update BookLoan l set l.returned = true where l.id = :bookLoanId")
    void MarkBookLoanAsReturned(@Param("bookLoanId") String bookLoanId);


}
