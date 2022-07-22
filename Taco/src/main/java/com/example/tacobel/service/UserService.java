package com.example.tacobel.service;

import com.example.tacobel.entity.RoleName;
import com.example.tacobel.entity.User;
import com.example.tacobel.repository.RoleRepository;
import com.example.tacobel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;


@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;


    @Autowired
    public void setUserRepository(UserRepository userRepository,
                                  RoleRepository roleRepository,
                                  PasswordEncoder passwordEncoder
                                  ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("user not found with this username : " + username);
        }
        return user;
    }


    private boolean saveUser(User user, RoleName roleName) {
        if (userRepository.findByUsername(user.getUsername()) != null)  return false;
        user.setRoles(Collections.singleton(roleRepository.findByName(roleName.name())));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (roleName.equals(RoleName.ROLE_ADMIN)) user.setStatus(true);
        userRepository.save(user);
        return true;
    }

    @Transactional
    public boolean saveAdmin(User user) {
        return saveUser(user, RoleName.ROLE_ADMIN);
    }

    @Transactional
    public boolean saveUser(User user) {
        return saveUser(user, RoleName.ROLE_USER);
    }


    public boolean deleteUser(User user) {
        if (userRepository.findByUsername(user.getUsername()) == null) {
            return false;
        }

        userRepository.delete(user);
        return true;
    }

    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    public User findByEmail(String email) {return this.userRepository.findByEmail(email);}
}
