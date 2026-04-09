package com.mar1a_ed.e_marketplace.service;

import com.mar1a_ed.e_marketplace.model.entity.User;
import com.mar1a_ed.e_marketplace.model.enums.Role;
import com.mar1a_ed.e_marketplace.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User create(User user){
        if(user.getRole()==null){
            user.setRole(Role.ROLE_CLIENT);
        }

        return userRepository.save(user);
    }

}
