package com.example.springbootchallenge.service.Impl;

import com.example.springbootchallenge.AppProperties;
import com.example.springbootchallenge.repository.User;
import com.example.springbootchallenge.repository.UserRepository;
import com.example.springbootchallenge.rest.dto.UserRegisteredEvent;
import com.example.springbootchallenge.service.intf.UserServiceIntf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachePut;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserServiceIntf {

    @Value("${app.config.threadPoolSize}")
    private Integer threadPoolSize;
    @Autowired
    private AppProperties appProperties;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void registerUser(Long userId) {
        // Perform the user registration logic
        System.out.println("User registered: " + userId);

        // Publish the UserRegisteredEvent
        UserRegisteredEvent event = new UserRegisteredEvent(this, userId);
        eventPublisher.publishEvent(event);
    }

    @Transactional
    public User registerUser(String name, String email) {
        // 1. Save the user to the database
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        userRepository.save(user);

        System.out.println("User saved in DB at " + System.currentTimeMillis());
        return user;
    }

    @Override
    public Slice<User> getUsersAsSlice(Pageable pageable) {
        return null;
    }

    private void sendWelcomeEmail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Welcome to Our Service");
        message.setText("Hello! Welcome to our service. We're glad to have you with us.");
        mailSender.send(message);
    }


    public Page<User> getAllUsers(PageRequest pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    @Cacheable("users")
    public User getUserById(Long id) {
        // Simulate an expensive operation, like a database query
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {

    }

    @CachePut(value = "users", key = "#user.id")
    //@CacheEvict(value = "users", key = "#user.id")
    public void updateUser(User user) {
        appProperties.getThreadPoolSize();
        userRepository.save(user);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void saveUserWithRequired(User user) {
        System.out.println("Saving User with REQUIRED: " + user.getName());
        userRepository.save(user);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveUserWithRequiresNew(User user) {
        System.out.println("Saving User with REQUIRES_NEW: " + user.getName());
        userRepository.save(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void processUsers() {
        System.out.println("Starting processUsers");

        User user1 = new User();
        user1.setName("Sunchit Dudeja");
        //user1.setId(1L);
        user1.setEmail("sunchitdudeja@gmail.com");
        saveUserWithRequired(user1);

        User user2 = new User();
        user2.setName("SRK");
        //user2.setId(2L);
        user2.setEmail("srk@gmail.com");
        saveUserWithRequiresNew(user2);

        System.out.println("Throwing exception to rollback processUsers transaction");
        throw new RuntimeException("Exception to rollback processUsers transaction");
    }


    public List<User> getUsersByNameAndEmail(String name, String email) {
        return null;//userRepository.findByNameAndEmail(name, email);
    }

    public List<User> getUsersByNamePartAndEmailDomain(String namePart, String emailDomain) {
        return null; //userRepository.findByNameContainingAndEmailEndingWith(namePart, emailDomain);
    }


    public List<User> searchUsers(Optional<String> name, Optional<String> email) {
        User user = new User();

        name.ifPresent(user::setName);
        email.ifPresent(user::setEmail);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("email", ExampleMatcher.GenericPropertyMatchers.startsWith());

        Example<User> example = Example.of(user, matcher);
        return userRepository.findAll(example);
    }

    @Transactional
    public void deleteUsersByIdsInBatch(List<Long> ids) {
        try {
            userRepository.deleteAllByIdInBatch(ids);
        } catch (DataIntegrityViolationException e) {
            System.out.println("Error: Unable to delete some users due to foreign key constraint violations.");
            // Optionally, rethrow the exception or log the issue
        } catch (Exception e) {
            System.out.println("Error: An unexpected error occurred while deleting users.");
        }
    }

    public boolean userExistsByNameAndEmail(Optional<String> name, Optional<String> email) {
        // Create a User instance with the name set
        User exampleUser = new User();
        exampleUser.setName(name.get());
        exampleUser.setEmail(email.get());

        // Create Example object
        Example<User> example = Example.of(exampleUser);

        // Call exists with the Example
        return userRepository.exists(example);
    }
}
