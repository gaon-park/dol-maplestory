package dol.example.service.impl;

import dol.example.common.exception.advice.APIException;
import dol.example.common.info.ExceptionInfo;
import dol.example.domain.TUser;
import dol.example.repository.TUserRepository;
import dol.example.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TUserServiceImpl implements TUserService {

    @Autowired
    TUserRepository tUserRepository;

    @Override
    public TUser findTUser(Long id) {
        return tUserRepository.findById(id).orElseThrow(() -> new APIException(ExceptionInfo.NOT_FOUND_EXCEPTION));
    }

    @Override
    public TUser findTUser(String email){
        return tUserRepository.findByEmail(email).orElseThrow(() -> new APIException(ExceptionInfo.NOT_FOUND_EXCEPTION));
    }

    @Override
    public TUser saveTUser(TUser tUser) {
        if(tUser.getEmail() == null || tUser.getEmail().isEmpty()){
            throw new APIException(ExceptionInfo.INVALID_REQUEST_EXCEPTION);
        }

        if(!tUserRepository.findByEmail(tUser.getEmail()).isEmpty()){
            throw new APIException(ExceptionInfo.ALREADY_EXIST_EXCEPTION);
        }

        return tUserRepository.save(tUser);
    }
}
