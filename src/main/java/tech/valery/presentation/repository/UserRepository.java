package tech.valery.presentation.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import tech.valery.presentation.model.Presentation;
import tech.valery.presentation.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    @Override
    List<User> findAll();

    @Override
    Page<User> findAll(Pageable pageable);

    @Override
    Optional<User> findById(Long aLong);

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(User entity);

    @Override
    <S extends User> S save(S entity);

    Optional<User> findByUsername(String username);

    List<User> findAllByPresentations(Presentation presentation);

    User findByEmail(String email);

}
