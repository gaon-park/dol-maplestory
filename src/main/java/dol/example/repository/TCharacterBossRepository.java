package dol.example.repository;

import dol.example.domain.TCharacterBoss;
import dol.example.domain.TCharacterBossPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TCharacterBossRepository extends JpaRepository<TCharacterBoss, TCharacterBossPK> {
    Optional<List<TCharacterBoss>> findByCharacterId(Long characterId);
}
