package com.practicaswrest.controller;

import com.practicaswrest.Dto.UserDto;
import com.practicaswrest.Modelo.Rol;
import com.practicaswrest.Modelo.Usuario;
import com.practicaswrest.repo.UserReposity;
import com.practicaswrest.service.IUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.practicaswrest.repo.RolRepositorio;

import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin("*")
public class UserController {


@Autowired
   private IUser iUser;

@Autowired
private UserReposity usuariorepositorio;

@Autowired RolRepositorio rolRepositorio;



    @GetMapping("/users/{id}")
    public ResponseEntity<?> buscarusuarioporid(@PathVariable Long id){

        Usuario result = iUser.buscarusuarioxid(id).get();

        if(result == null || !iUser.Existeelusuarioporid(id)){
            return new ResponseEntity<>("mensaje: No se encuentra el usuario registrado",HttpStatus.BAD_REQUEST);
        }


        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(result,userDto);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping("/users/")
    public List<Usuario> listartodoslosusuarios(){
        return iUser.listarUsuarios();
    }

    @PostMapping("/users/")
    public ResponseEntity<?> Crear(@RequestBody Usuario user){
        if(usuariorepositorio.existsByUsername(user.getUsername())) {
            return new ResponseEntity<>("Ese nombre de usuario ya existe",HttpStatus.BAD_REQUEST);
        }

        if(usuariorepositorio.existsByEmail(user.getEmail())) {
            return new ResponseEntity<>("Ese email de usuario ya existe",HttpStatus.BAD_REQUEST);
        }

        Rol roles = rolRepositorio.findByNombre("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));
        Usuario result = iUser.crearUsuario(user);


           UserDto userDto = new UserDto();
           BeanUtils.copyProperties(result,userDto);
           return new ResponseEntity<>(userDto, HttpStatus.OK);

    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/users/{id}")
    public ResponseEntity<?> Actualizar(@PathVariable Long id, @RequestBody Usuario user){

        if(id == 0){
            return new ResponseEntity<>(" el usuario no se encuentra registrado",HttpStatus.BAD_REQUEST);
        }
        if(!iUser.Existeelusuarioporid(id)){
            return new ResponseEntity<>(" el usuario no se encuentra registrado",HttpStatus.BAD_REQUEST);
        }

        Usuario result = iUser.actualizarusuario(id,user);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(result,userDto);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> Eliminar(@PathVariable Long id){


        if(!iUser.Existeelusuarioporid(id)){
            return new ResponseEntity<>(" el usuario no se encuentra registrado",HttpStatus.BAD_REQUEST);
        }

        iUser.eliminarusuario(id);
        return new ResponseEntity<>("el usuario se ha eliminado sastifactoriamente",HttpStatus.OK);

    }







}
