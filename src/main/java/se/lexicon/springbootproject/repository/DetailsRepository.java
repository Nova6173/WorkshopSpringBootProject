package se.lexicon.springbootproject.repository;
import se.lexicon.springbootproject.entity.Details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailsRepository extends JpaRepository<Details,String> {
    Details findDetailsByEmail(String email);
    Details findDetailsByNameContains(String name);
    Details findDetailsByNameIgnoreCase(String name);
}