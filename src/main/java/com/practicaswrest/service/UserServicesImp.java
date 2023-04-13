package com.practicaswrest.service;

import com.practicaswrest.Modelo.User;
import com.practicaswrest.repo.UserReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServicesImp implements IUser{


    @Autowired
    private UserReposity userReposity;

    @Autowired EncryptServiceImp encryptServiceImp;


    @Override
    public User crearUsuario(User user) {
        user.setPassword(encryptServiceImp.encryptpassword(user.getPassword()));
        return userReposity.save(user);
    }



    @Override
    public void eliminarusuario(int id) {
          userReposity.deleteById(id);
    }

    @Override
    public User actualizarusuario(int id,User user) {
        User old = userReposity.getById(id);
        old.setFullname(user.getFullname());

        old.setPassword(encryptServiceImp.encryptpassword(user.getPassword()));
        old.setCorreo(user.getCorreo());

        return userReposity.save(old);
    }

    @Override
    public boolean Existeelusuarioporid(int id) {
        return userReposity.existsById(id);
    }

    @Override
    public Optional<User> buscarusuarioxid(int id) {

        return userReposity.findById(id);
    }


    @Override
    public List<User> listarUsuarios() {
        return userReposity.findAll();
    }
}
