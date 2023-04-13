package com.practicaswrest.controller;

import com.practicaswrest.Modelo.Booking;
import com.practicaswrest.Enumeraciones.BookingStatus;
import com.practicaswrest.service.IBooking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookingController {


    @Autowired
    private IBooking iBooking;


    @GetMapping("/booking/{id}")
    public ResponseEntity<?> buscarReservaporid(@PathVariable int id){



        if(!iBooking.existereservaporid(id)){
            return new ResponseEntity<>("mensaje: No se encuentra la reserva realizada", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(iBooking.findbyid(id).get(), HttpStatus.OK);
    }


    @GetMapping("/booking/flight/{id}")
    public ResponseEntity<?> listarReservasPorVuelo(@PathVariable int id){
        if(iBooking.findbyfligth(id).size() == 0){
            return new ResponseEntity<>("mensaje: no hay reservas para ese vuelo, en este momento", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(iBooking.findbyfligth(id),HttpStatus.OK);
    }

    @PostMapping("/booking/flight/{idflight}/user/{userid}")
    public ResponseEntity<?> Crear(@RequestBody Booking booking,@PathVariable int idflight,  @PathVariable Long userid){

        Booking result = iBooking.crear(booking,userid,idflight);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/booking/{id}")
    public ResponseEntity<?> Eliminar(@PathVariable int id){


        if(!iBooking.existereservaporid(id)){
            return new ResponseEntity<>(" la reserva no se encuentra registrado",HttpStatus.BAD_REQUEST);
        }

        iBooking.eliminar(id);
        return new ResponseEntity<>("la reserva se ha eliminado sastifactoriamente",HttpStatus.OK);

    }

    @GetMapping("/booking/")
    public ResponseEntity<?> obtener(@RequestParam (required = false) BookingStatus status, @RequestParam (required = false) Integer id){
        List<Booking> result;

        if(status == null && id == null){
            result = iBooking.Listarvuelos();
        }else{
            result = iBooking.listarxStatusYcustomer(status,id);
        }

        if (status == null && id != null) {
            result = iBooking.findbycustomer(id);
        } else{
            result = iBooking.findbystatus(status);
        }


        return new ResponseEntity<>(result,HttpStatus.OK);
    }




}

