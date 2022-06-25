package dol.example.service;

import dol.example.domain.TCharacter;
import dol.example.domain.TUser;

public interface TUserService {
    TUser findTUser(Long id);

    TUser findTUser(String email);

    TUser saveTUser(TUser tUser);
}
