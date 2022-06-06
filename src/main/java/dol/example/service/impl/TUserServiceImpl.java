package dol.example.service.impl;

import dol.example.domain.TUser;
import dol.example.repository.TUserRepository;
import dol.example.service.TUserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TUserServiceImpl implements TUserService {

    @Autowired
    TUserRepository tUserRepository;

    @Override
    public TUser findTUser(Long id) throws NotFoundException {
        return tUserRepository.findById(id).orElseThrow(() -> new NotFoundException("존재하지 않는 userId : " + id));
    }

    @Override
    public TUser saveTUser(TUser tUser) {
        return tUserRepository.save(tUser);
    }
}
