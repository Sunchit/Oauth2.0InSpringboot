package com.example.springbootchallenge.service.intf;

import com.example.springbootchallenge.repository.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

public interface UserServiceIntf {
    Page<User> getAllUsers(PageRequest pageable);
    User getUserById(Long id);
    User saveUser(User user);
    void deleteUser(Long id);
    void updateUser(User user);
    void processUsers();

    List<User> getUsersByNamePartAndEmailDomain(String namePart, String emailDomain);

    List<User> getUsersByNameAndEmail(String name, String email);
    List<User> searchUsers(Optional<String> name, Optional<String> email);

    void deleteUsersByIdsInBatch(List<Long> ids);
    boolean userExistsByNameAndEmail(Optional<String> name, Optional<String> email);
    void registerUser(Long userId);
    User registerUser(String name, String email);

    Slice<User> getUsersAsSlice(Pageable pageable);
}

