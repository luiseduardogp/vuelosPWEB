package com.practicaswrest.service;

import com.practicaswrest.Modelo.User;

import java.util.List;
import java.util.Optional;

public interface IUser {

     User crearUsuario(User user);

     void eliminarusuario(int id);

     User actualizarusuario(int id, User user);


      boolean Existeelusuarioporid(int id);


      Optional<User> buscarusuarioxid(int id);
     List<User> listarUsuarios();




}
