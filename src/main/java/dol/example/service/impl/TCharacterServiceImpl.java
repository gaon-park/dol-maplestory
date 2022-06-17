package dol.example.service.impl;

import dol.example.common.exception.advice.APIException;
import dol.example.common.info.ExceptionInfo;
import dol.example.domain.TCharacter;
import dol.example.domain.TUnion;
import dol.example.domain.TUser;
import dol.example.repository.TCharacterRepository;
import dol.example.repository.TUnionRepository;
import dol.example.repository.TUserRepository;
import dol.example.service.TCharacterService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TCharacterServiceImpl implements TCharacterService {

    @Autowired
    TCharacterRepository tCharacterRepository;

    @Autowired
    TUserRepository tUserRepository;

    @Autowired
    TUnionRepository tUnionRepository;

    @Override
    public List<TCharacter> saveTCharacterList(Long userId, List<TCharacter> tCharacterList) throws APIException {
        TUser tUser = tUserRepository.findById(userId).orElseThrow(() -> new APIException(ExceptionInfo.INVALID_REQUEST_EXCEPTION, "존재하지 않는 유저"));
        for(TCharacter tCharacter : tCharacterList){
            tCharacter.setUser(tUser);
        }

        return tCharacterRepository.saveAll(tCharacterList);
    }

    @Override
    public TCharacter findTCharacterById(Long id) {
        return tCharacterRepository.findById(id).orElseThrow(() -> new APIException(ExceptionInfo.NOT_FOUND_EXCEPTION));
    }


}
