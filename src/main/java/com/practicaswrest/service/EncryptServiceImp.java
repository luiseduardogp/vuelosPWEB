package com.practicaswrest.service;

import com.practicaswrest.repo.UserReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class EncryptServiceImp implements IEncryptService{



    @Autowired
    private UserReposity userReposity;
    @Override
    public String encryptpassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public boolean verifypassword(String origialpassword, String hashpassword) {
        return false;
    }
}
