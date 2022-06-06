package dol.example.repository;

import dol.example.domain.TCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TCharacterRepository extends JpaRepository<TCharacter, Long> {
}
