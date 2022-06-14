package dol.example.repository;

import dol.example.domain.TUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TUserRepository extends JpaRepository<TUser, Long> {
    Optional<TUser> findByEmail(String email);
}
