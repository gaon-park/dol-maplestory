package dol.example.repository;

import dol.example.domain.TCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TCharacterRepository extends JpaRepository<TCharacter, Long> {
    Optional<TCharacter> findByUserIdAndCharacterName(Long userId, String characterName);
}
