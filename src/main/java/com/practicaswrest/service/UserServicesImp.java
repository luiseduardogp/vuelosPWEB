package com.practicaswrest.service;

import com.practicaswrest.Modelo.Usuario;
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
    public Usuario crearUsuario(Usuario user) {
        user.setPassword(encryptServiceImp.encryptpassword(user.getPassword()));
        return userReposity.save(user);
    }

    @Override
    public void eliminarusuario(Long id) {
        userReposity.deleteById(id);
    }


    @Override
    public Usuario actualizarusuario(Long id,Usuario user) {
        Usuario old = userReposity.getById(id);
        old.setNombre(user.getNombre());
        old.setUsername(user.getUsername());

        old.setPassword(encryptServiceImp.encryptpassword(user.getPassword()));
        old.setEmail(user.getEmail());

        return userReposity.save(old);
    }

    @Override
    public boolean Existeelusuarioporid(Long id) {
        return userReposity.existsById(id);
    }

    @Override
    public Optional<Usuario> buscarusuarioxid(Long id) {

        return userReposity.findById(id);
    }


    @Override
    public List<Usuario> listarUsuarios() {
        return userReposity.findAll();
    }
}
