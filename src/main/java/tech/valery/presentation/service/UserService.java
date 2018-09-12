package tech.valery.presentation.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tech.valery.presentation.model.Presentation;
import tech.valery.presentation.model.User;

import javax.transaction.Transactional;
import java.util.List;

public interface UserService {
    void save(User user);

    List<User> findAll();

    User findOne(Long id);

    void deleteUser(Long id);

    List<User> findAllByPresentations(Presentation presentation);

    User findByEmail(String email);

    User findByUsername(String username);

    Page<User> findAll(Pageable pageable);
}
