package dol.example.service;

import dol.example.domain.TCharacter;
import dol.example.domain.TUnion;
import dol.example.dto.common.UnionDetail;

import java.util.List;

public interface TUnionService {
    TUnion saveTUnion(TUnion tUnion);

    UnionDetail getUnionDetail(List<TCharacter> list);
}
