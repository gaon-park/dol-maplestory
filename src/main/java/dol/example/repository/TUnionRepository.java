package dol.example.repository;

import dol.example.common.info.WorldInfo;
import dol.example.domain.TUnion;
import dol.example.domain.TUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TUnionRepository extends JpaRepository<TUnion, Long> {
    Optional<TUnion> findByUserAndWorldInfo(TUser tUser, WorldInfo worldInfo);
}
