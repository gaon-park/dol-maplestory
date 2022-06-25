package dol.example.repository;

import dol.example.domain.TCharacterBoss;
import dol.example.domain.TCharacterBossPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TCharacterBossRepository extends JpaRepository<TCharacterBoss, TCharacterBossPK> {
}
