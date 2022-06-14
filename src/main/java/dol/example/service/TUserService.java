package dol.example.service;

import dol.example.domain.TUser;
import javassist.NotFoundException;

public interface TUserService {
    public TUser findTUser(Long id) throws NotFoundException;
    public TUser findTUser(String email);
    public TUser saveTUser(TUser tUser);
}
