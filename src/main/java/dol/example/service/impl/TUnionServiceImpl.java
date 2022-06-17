package dol.example.service.impl;

import dol.example.domain.TUnion;
import dol.example.repository.TUnionRepository;
import dol.example.repository.TUserRepository;
import dol.example.service.TUnionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TUnionServiceImpl implements TUnionService {

    @Autowired
    TUnionRepository tUnionRepository;

    @Autowired
    TUserRepository tUserRepository;

    @Override
    public TUnion saveTUnion(TUnion tUnion) {
        return tUnionRepository.save(tUnion);
    }
}
