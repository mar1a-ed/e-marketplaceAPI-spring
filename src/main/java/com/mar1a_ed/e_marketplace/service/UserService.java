package com.mar1a_ed.e_marketplace.service;

import com.mar1a_ed.e_marketplace.exception.EmptyUsersException;
import com.mar1a_ed.e_marketplace.exception.UserNotFoundException;
import com.mar1a_ed.e_marketplace.exception.UsernameUniqueViolationException;
import com.mar1a_ed.e_marketplace.model.entity.User;
import com.mar1a_ed.e_marketplace.model.enums.Role;
import com.mar1a_ed.e_marketplace.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User create(User user){

        try{
            if(user.getRole()==null){
                user.setRole(Role.ROLE_CLIENT);
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }catch (Exception e){
            throw new UsernameUniqueViolationException(String.format("Username {%s} already registered", user.getUsername()));
        }

    }

    @Transactional(readOnly = true)
    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(String.format("User 'id = %s' not found.", id))
        );
    }

    @Transactional(readOnly = true)
    public List<User> findAll(){
        List<User> users = userRepository.findAll();

        if(!users.isEmpty()){
            return users;
        }

        throw new EmptyUsersException("There are no registered users.");
    }

    @Transactional
    public User updatePassword(Long id, String currentPassword, String newPassword, String confirmPassword){
        if(!newPassword.equals(confirmPassword)){
            throw new RuntimeException("The 'Confirm Password' does not match with 'New Password'.");
        }

        User user = userRepository.getReferenceById(id);

        if(!passwordEncoder.matches(currentPassword, user.getPassword())){
            throw new RuntimeException("The 'Current Password' does not match with the right password.");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        return user;
    }
}
