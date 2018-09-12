package tech.valery.presentation.service;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.valery.presentation.model.Presentation;
import tech.valery.presentation.model.Role;
import tech.valery.presentation.model.User;
import tech.valery.presentation.repository.RoleRepository;
import tech.valery.presentation.repository.UserRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void save(User user) {

//        //todo encrypt pasword
//        user.setActive(1);
//        Role userRole = roleRepository.findByRole("ADMIN");
//        user.setRole(userRole);
        userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findOne(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findAllByPresentations(Presentation presentation) {
        return userRepository.findAllByPresentations(presentation);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
