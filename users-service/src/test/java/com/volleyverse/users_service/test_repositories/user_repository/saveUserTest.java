package com.volleyverse.users_service.test_repositories.user_repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * The tests will be name with this structure: nameMethod_functionalitySuccess or nameMethod_functionalitySuccess¿Why?
 * This class will verify that the methods of saving users will work in the following situations:
 */
@DataJpaTest
class saveUserTest {
	
    @Autowired
    private UserRepository userRepository;

    /**
     * Before all test we make sure the user John Doe doesn't exists in the data base
     */
    @BeforeEach
    void setUp() {
        // Borra solo el usuario con email "johndoe@example.com" si existe
        userRepository.deleteByEmail("johndoe@example.com");  
    }

    /**
     * This test will save the user of John Doe, it has to save the user.
     */
    @Test
    void save_savedSuccess() {
        User user = new User(null, "johndoe@example.com", "JohnDoe1!", "John Doe");
        
        User savedUser = userRepository.save(user);
        
        assertNotNull(savedUser.getId());
        assertEquals("johndoe@example.com", savedUser.getEmail());
        assertEquals("John Doe", savedUser.getName());
    }
    
    /**
     * This test will update the user name of John Doe to Sam Las, the user's name doesn't have restriction so it can be repeated, it has to update the user.
     */
    @Test
    void save_updatedSuccess() {
        User user = new User(null, "johndoe@example.com", "JohnDoe1!", "John Doe");
        User savedUser = userRepository.save(user);
        
        savedUser.setName("Sam Las");
        User updateUser = userRepository.save(savedUser);
        
        assertEquals(savedUser.getId(), updateUser.getId());
        assertEquals("Sam Las", updateUser.getName());
    }

    /**
     * This test will try to update an user email to an email that already exists in the database, how email has to be unique it wouldn't work and will throw an exception.
     */
    @Test
    void save_updatedFailureEmailAlreadyExists() {
        User user1 = new User(null, "johndoe@example.com", "JohnDoe1!", "John Doe");
        User user2 = new User(null, "samlas@example.com", "JohnDoe1!", "Sam Las");
        userRepository.save(user1);
        userRepository.save(user2);
        
        user1.setEmail("samlas@example.com");

        assertThrows(DataIntegrityViolationException.class, () -> userRepository.save(user1));
    }
    
    /**
     * This test will try to save a new user with an email that is already register in the database, how the email has to be unique it wouldn't work and will throw an exception.
     */
    @Test
    void save_savedFailureEmailAlreadyExists() {
        User user1 = new User(null, "johndoe@example.com", "JohnDoe1!", "John Doe");
        userRepository.save(user1);

        User user2 = new User(null, "johndoe@example.com", "SamLas1!!", "Sam Las");

        assertThrows(DataIntegrityViolationException.class, () -> userRepository.save(user2));
    }
}
