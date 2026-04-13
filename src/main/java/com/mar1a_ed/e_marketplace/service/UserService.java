package com.mar1a_ed.e_marketplace.service;

import com.mar1a_ed.e_marketplace.exception.NoRegisteredUsersException;
import com.mar1a_ed.e_marketplace.exception.UserNotFoundException;
import com.mar1a_ed.e_marketplace.exception.UsernameUniqueViolationException;
import com.mar1a_ed.e_marketplace.model.entity.User;
import com.mar1a_ed.e_marketplace.model.enums.Role;
import com.mar1a_ed.e_marketplace.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User create(User user) {
        try {
            if (user.getRole() == null) {
                user.setRole(Role.ROLE_CLIENT);
            }

            userRepository.save(user);

            return user;

        }catch (Exception e){
            throw new UsernameUniqueViolationException(String.format("Username {%s} already registered", user.getUsername()));
        }
    }

    @Transactional(readOnly = true)
    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(String.format("User id=%s not found.", id))
        );
    }

    @Transactional(readOnly = true)
    public List<User> findAll(){
        try{
            return userRepository.findAll();
        }catch (Exception e){
            throw new NoRegisteredUsersException("No registered users.");
        }
    }

    @Transactional
    public void updatePassword(Long id, String currentPassword, String newPassword, String confirmPassword){
        User user = findById(id);
        String originalPassword = user.getPassword();

        if(!currentPassword.equals(originalPassword)){
            throw new RuntimeException("The current password does not match.");
        }

        if(!newPassword.equals(confirmPassword)){
            throw new RuntimeException("The 'newPassword' does not match with 'confirmPassword'");
        }

        user.setPassword(newPassword);
        userRepository.save(user);
    }
}
