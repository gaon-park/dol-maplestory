package dol.example.service.impl;

import dol.example.common.exception.AlreadyExistException;
import dol.example.common.exception.InvalidValueException;
import dol.example.domain.TUser;
import dol.example.repository.TUserRepository;
import dol.example.service.TUserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.BadAttributeValueExpException;
import javax.management.InvalidAttributeValueException;

@Service
public class TUserServiceImpl implements TUserService {

    @Autowired
    TUserRepository tUserRepository;

    @Override
    public TUser findTUser(String email){
        return tUserRepository.findByEmail(email);
    }

    @Override
    public TUser findTUser(Long id) throws NotFoundException {
        return tUserRepository.findById(id).orElseThrow(() -> new NotFoundException("존재하지 않는 userId : " + id));
    }

    @Override
    public TUser saveTUser(TUser tUser) {
        if((tUser.getEmail() == null || tUser.getEmail().isEmpty())
        || (tUser.getRepresentativeCharacterName() == null || tUser.getRepresentativeCharacterName().isEmpty())){
            throw new InvalidValueException("email and representativeCharacterName cannot be null");
        }
        if(findTUser(tUser.getEmail()) != null){
            return tUserRepository.save(tUser);
        }
        throw new AlreadyExistException("duplicate email");
    }
}
