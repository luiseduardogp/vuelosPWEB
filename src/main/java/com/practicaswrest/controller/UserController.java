package com.practicaswrest.controller;

import Dto.UserDto;
import com.practicaswrest.Modelo.User;
import com.practicaswrest.service.IUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {


@Autowired
   private IUser iUser;



    @GetMapping("/users/{id}")
    public ResponseEntity<?> buscarusuarioporid(@PathVariable int id){

        User result = iUser.buscarusuarioxid(id).get();

        if(result == null || !iUser.Existeelusuarioporid(id)){
            return new ResponseEntity<>("mensaje: No se encuentra el usuario registrado",HttpStatus.BAD_REQUEST);
        }


        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(result,userDto);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping("/users/")
    public List<User> listartodoslosusuarios(){
        return iUser.listarUsuarios();
    }

    @PostMapping("/users/")
    public ResponseEntity<?> Crear(@RequestBody User user){
           User result = iUser.crearUsuario(user);


           UserDto userDto = new UserDto();
           BeanUtils.copyProperties(result,userDto);
           return new ResponseEntity<>(userDto, HttpStatus.OK);

    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> Actualizar(@PathVariable int id, @RequestBody User user){

        if(id == 0){
            return new ResponseEntity<>(" el usuario no se encuentra registrado",HttpStatus.BAD_REQUEST);
        }
        if(!iUser.Existeelusuarioporid(id)){
            return new ResponseEntity<>(" el usuario no se encuentra registrado",HttpStatus.BAD_REQUEST);
        }

        User result = iUser.actualizarusuario(id,user);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(result,userDto);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> Eliminar(@PathVariable int id){


        if(!iUser.Existeelusuarioporid(id)){
            return new ResponseEntity<>(" el usuario no se encuentra registrado",HttpStatus.BAD_REQUEST);
        }

        iUser.eliminarusuario(id);
        return new ResponseEntity<>("el usuario se ha eliminado sastifactoriamente",HttpStatus.OK);

    }







}
