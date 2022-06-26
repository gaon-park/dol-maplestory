package dol.example.repository;

import dol.example.domain.TCharacterQuest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TCharacterQuestRepository extends JpaRepository<TCharacterQuest, Long> {
}
