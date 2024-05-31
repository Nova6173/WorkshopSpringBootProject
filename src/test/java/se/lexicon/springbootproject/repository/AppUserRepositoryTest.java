package se.lexicon.springbootproject.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.springbootproject.entity.AppUser;
import se.lexicon.springbootproject.entity.Details;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@DataJpaTest
public class AppUserRepositoryTest {

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    DetailsRepository detailsRepository;

    @Transactional
    @Test
    public void testSaveAndFindAppUserByUserName(String username) {
        //1. Arrange
        Details userDetails = new Details("Test Testsson, test@test.se", "test@test.se", LocalDate.now());
        AppUser appUser = new AppUser("testuser", "IaMATestUser");

        //2.Act
        AppUser savedUser = appUserRepository.save(appUser);

        //3. Assert
        Assertions.assertNotNull(savedUser);
        Assertions.assertNotNull(savedUser.getId());
        Assertions.assertEquals(appUser, appUserRepository.findAppUserByUsername("testuser"));
        /*Optional<AppUser> foundUser = appUserRepository.findAppUserByUsername("testuser");
        Assertions.assertTrue(foundUser.isPresent());*/
    }

    @Transactional
    @Test
    public void testFindUserByRegDateBetween() {

        //1.Arrange
        Details userDetails = new Details("Test Testson, test@test.se", "test@test.se", LocalDate.now());
        AppUser appUser = new AppUser("testuser", "IaMATestUser");
        AppUser appUserTwo = new AppUser("testusertwo", "IaMATestUserTwo");

        //2. Act
        AppUser savedUser = appUserRepository.save(appUser);
        AppUser savedUserTwo = appUserRepository.save(appUserTwo);
        List<AppUser> resultList = appUserRepository.findAppUserByRegDateBetween(LocalDate.parse("2024-05-07"), LocalDate.parse("2024-06-07"));

        //3. Assert
        Assertions.assertTrue(resultList.contains(appUser));
        Assertions.assertTrue(resultList.contains(appUserTwo));
    }

    @Transactional
    @Test
    public void testFindAppUserByUserDetails_Id() {

        //1. Arrange
        Details userDetails = new Details("Test Testsson, test@test.se", "test@test.se", LocalDate.now());
        AppUser appUser = new AppUser("testuser", "IaMATestUser");
        appUser.setUserDetails(userDetails);

        //2. Act
        AppUser savedUser = appUserRepository.save(appUser);
        Details savedDetails = detailsRepository.save(userDetails);
        int detailsId = userDetails.getId();

        //3. Assert
        Assertions.assertEquals(appUser, appUserRepository.findAppUserByUserDetails_Id(detailsId));

    }

    @Transactional
    @Test
    public void testFindAppUserByUserEmail() {

        //1. Arrange
        Details userDetails = new Details("Test Testsson, test@test.se", "test@test.se", LocalDate.now());
        AppUser appUser = new AppUser("testuser", "IaMATestUser");
        appUser.setUserDetails(userDetails);

        //2. Act
        AppUser savedUser = appUserRepository.save(appUser);
        Details savedDetails = detailsRepository.save(userDetails);
        String userEmail = userDetails.getEmail();

        //3. Assert
        Assertions.assertEquals(appUser, appUserRepository.findAppUserByUserDetails_EmailIgnoreCase(userEmail));
    }
    
    @Transactional
    @Test

            public void testUpdateUserAppUser(){

        //1. Arrange
        Details userDetails = new Details ("Test Testsson", "test@test.se", LocalDate.now());
        AppUser appUser = new AppUser("testuser", "IaMATestUser");
        appUser.setUserDetails(userDetails);
        appUserRepository.save(appUser);

        // 2. Act
        appUser.setUsername("updateduser");
        appUserRepository.save(appUser);

        //3. Assert
        Optional<AppUser> updatedUser = testSaveAndFindAppUserByUserName("updateduser");
        Assertions.assertTrue(updatedUser.isPresent());
        Assertions.assertEquals("updateduser", updatedUser.get().getUsername());


    }
    @Transactional
    @Test
    public void testDeleteAppUser(){

        //1. Arrange
        Details userDetails = new Details("Test Testsson", "test@test.se", LocalDate.now());
        AppUser appUser = new AppUser("testuser", "IaMATestUser");
        appUser.setUserDetails(userDetails);
        appUserRepository.save(appUser);
        int userId = appUser.getId();

        //2 Act

        appUserRepository.deleteById(userId);

        //3. Assert

        Optional<AppUser> deletedUser = appUserRepository.findById(userId);
        Assertions.assertFalse(deletedUser.isPresent());
    }


    

}
