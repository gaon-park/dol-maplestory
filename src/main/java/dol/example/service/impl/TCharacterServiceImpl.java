package dol.example.service.impl;

import dol.example.common.exception.advice.APIException;
import dol.example.common.info.ExceptionInfo;
import dol.example.domain.TCharacter;
import dol.example.domain.TUnion;
import dol.example.domain.TUser;
import dol.example.dto.common.CharacterDetail;
import dol.example.repository.TCharacterRepository;
import dol.example.repository.TUnionRepository;
import dol.example.repository.TUserRepository;
import dol.example.service.TCharacterService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TCharacterServiceImpl implements TCharacterService {

    @Autowired
    TCharacterRepository tCharacterRepository;

    @Override
    public List<TCharacter> saveTCharacterList(List<TCharacter> tCharacterList) {
        List<TCharacter> result = new ArrayList<>();
        for (TCharacter tCharacter : tCharacterList) {
            if (tCharacterRepository.findByUserIdAndCharacterName(tCharacter.getUser().getId(), tCharacter.getCharacterName()).isEmpty()) {
                result.add(tCharacterRepository.save(tCharacter));
            }
        }

        return result;
    }

    @Override
    public TCharacter findTCharacterById(Long id) {
        return tCharacterRepository.findById(id).orElseThrow(() -> new APIException(ExceptionInfo.NOT_FOUND_EXCEPTION));
    }
}
