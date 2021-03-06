package dol.example.service;

import dol.example.domain.TCharacter;
import dol.example.domain.TUnion;
import dol.example.domain.TUser;
import dol.example.dto.common.UnionDetail;

import java.util.List;

public interface TUnionService {
    TUnion saveTUnion(TUnion tUnion);

    List<UnionDetail> findTUnionByUser(TUser tUser);

    UnionDetail findTUnionByUserAndWorldInfoId(TUser tUser, Integer worldInfoId);

    UnionDetail getUnionDetail(List<TCharacter> list, Integer worldInfoId);
}
