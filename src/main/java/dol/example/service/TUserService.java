package dol.example.service;

import dol.example.repository.TUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TUserService {

    @Autowired
    TUserRepository tUserRepository;


}
