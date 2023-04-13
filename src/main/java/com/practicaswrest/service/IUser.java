package com.practicaswrest.service;

import com.practicaswrest.Modelo.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUser {

     Usuario crearUsuario(Usuario user);

     void eliminarusuario(Long id);

     Usuario actualizarusuario(Long id, Usuario user);


      boolean Existeelusuarioporid(Long id);


      Optional<Usuario> buscarusuarioxid(Long id);
     List<Usuario> listarUsuarios();




}
