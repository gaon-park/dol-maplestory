package dol.example.repository;

import dol.example.domain.TQuestOfCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TQuestOfCharacterRepository extends JpaRepository<TQuestOfCharacter, Long> {
}
