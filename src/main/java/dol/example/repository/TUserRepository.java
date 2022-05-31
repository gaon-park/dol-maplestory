package dol.example.repository;

import dol.example.domain.TUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TUserRepository extends JpaRepository<TUser, Long> {
}
