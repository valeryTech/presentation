package tech.valery.presentation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.valery.presentation.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);
}
