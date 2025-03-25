package com.volleyverse.users_service.test_repositories.user_repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

@DataJpaTest
class saveUserTest {
	
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        // Borra solo el usuario con email "johndoe@example.com" si existe
        userRepository.deleteByEmail("johndoe@example.com");  
    }

    @Test
    void save_savedSuccess() {
        User user = new User(null, "johndoe@example.com", "JohnDoe1!", "John Doe");
        
        User savedUser = userRepository.save(user);
        
        assertNotNull(savedUser.getId());
        assertEquals("johndoe@example.com", savedUser.getEmail());
        assertEquals("John Doe", savedUser.getName());
    }
    
    @Test
    void save_updatedSuccess() {
        User user = new User(null, "johndoe@example.com", "JohnDoe1!", "John Doe");
        User savedUser = userRepository.save(user);
        
        savedUser.setName("Sam Las");
        User updateUser = userRepository.save(savedUser);
        
        assertEquals(savedUser.getId(), updateUser.getId());
        assertEquals("Sam Las", updateUser.getName());
    }

    @Test
    void save_updatedFailureEmailAlreadyExists() {
        User user1 = new User(null, "johndoe@example.com", "JohnDoe1!", "John Doe");
        User user2 = new User(null, "samlas@example.com", "JohnDoe1!", "Sam Las");
        userRepository.save(user1);
        userRepository.save(user2);
        
        user1.setEmail("samlas@example.com");

        assertThrows(DataIntegrityViolationException.class, () -> userRepository.save(user1));
    }

    @Test
    void save_notSavedEmailAlreadyExists() {
        User user1 = new User(null, "johndoe@example.com", "JohnDoe1!", "John Doe");
        userRepository.save(user1);

        User user2 = new User(null, "johndoe@example.com", "SamLas1!!", "Sam Las");

        assertThrows(DataIntegrityViolationException.class, () -> userRepository.save(user2));
    }
}
