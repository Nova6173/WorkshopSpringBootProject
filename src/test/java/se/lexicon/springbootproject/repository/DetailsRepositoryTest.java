package se.lexicon.springbootproject.repository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.springbootproject.entity.Details;

import java.time.LocalDate;
import java.util.Optional;



    @DataJpaTest
    public class DetailsRepositoryTest {

        @Autowired
        DetailsRepository detailsRepository;

        @Transactional
        @Test
        public void testFindDetailsByEmail() {

            //1. Arrange
            Details userDetails = new Details("Test Testsson", "test@test.se", LocalDate.now());

            //2. Act
            Details savedDetails = detailsRepository.save(userDetails);
            Optional<Details> foundDetails = Optional.of(detailsRepository.findDetailsByEmail("test@test.se"));

            //3. Assert
            Assertions.assertTrue(foundDetails.isPresent());
        }

        @Transactional
        @Test
        public void testFindByNameContains() {

            //1. Arrange
            Details userDetails = new Details("Test Testsson", "test@test.se", LocalDate.now());

            //2. Act
            Details savedDetails = detailsRepository.save(userDetails);
            Optional<Details> foundDetails = Optional.of(detailsRepository.findDetailsByNameContains("Test"));

            //3. Assert
            Assertions.assertTrue(foundDetails.isPresent());
        }

        @Transactional
        @Test
        public void testFindByNameIgnoreCase() {

            //1. Arrange
            Details userDetails = new Details("Test Testsson", "test@test.se", LocalDate.now());

            //2. Act
            Details savedDetails = detailsRepository.save(userDetails);

            //3. Assert
            Assertions.assertEquals(userDetails,detailsRepository.findDetailsByNameIgnoreCase("tEst tEstsson"));
        }

    }


