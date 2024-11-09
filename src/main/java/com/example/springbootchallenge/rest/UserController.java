package com.example.springbootchallenge.rest;

import com.example.springbootchallenge.repository.User;
import com.example.springbootchallenge.rest.dto.IdsRequest;
import com.example.springbootchallenge.service.EmailService;
import com.example.springbootchallenge.service.intf.UserServiceIntf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceIntf userService;

    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private EmailService emailService;

    @PostMapping
    public User createUser(@RequestBody User user) throws Exception {
        return userService.registerUser(user.getName(), user.getEmail());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable String id, @RequestBody User user) {
        userService.updateUser(user);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PostMapping("/process")
    public String processUsers() {
        try {
            userService.processUsers();
        } catch (Exception e) {
            return "Transaction rolled back: " + e.getMessage();
        }
        return "Users processed";
    }



    @GetMapping("/find")
    public List<User> findUsers(@RequestParam String name, @RequestParam String email) {
        return userService.getUsersByNameAndEmail(name, email);
    }

    @GetMapping("/searchByNameAndEmailDomain")
    public List<User> searchUsersByNameAndEmailDomain(@RequestParam String namePart, @RequestParam String emailDomain) {
        return userService.getUsersByNamePartAndEmailDomain(namePart, emailDomain);
    }


    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUsers(
            @RequestParam Optional<String> name,
            @RequestParam Optional<String> email) {

        if(userService.userExistsByNameAndEmail(name, email)) {
            // Call the service method to search users based on the optional parameters
            List<User> users = userService.searchUsers(name, email);

            // Return 204 No Content if no users are found
            if (users.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            // Return 200 OK with the list of users if found
            return ResponseEntity.ok(users);
        } else {
            return null;
        }
    }
    @DeleteMapping("/deleteByIds")
    public String deleteUsersByIds(@RequestBody IdsRequest request) {
        userService.deleteUsersByIdsInBatch(request.getIds());
        return "Selected users have been deleted.";
    }


    @GetMapping("/sendEmail")
    public String sendEmail() {
        emailService.sendSimpleEmail("sunchitdudeja@gmail.com", "Learning to send Test Email in SpringBoot", "This is a test email.");
        return "Email Sent!";
    }

    @GetMapping
    public Page<User> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return userService.getAllUsers(pageable);
    }

    // Return as Slice without total pages or total elements
    @GetMapping("/slice")
    public Slice<User> getUsersAsSlice(Pageable pageable) {
        return userService.getUsersAsSlice(pageable);
    }

}
