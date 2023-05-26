package com.practicaswrest.controller;

import com.practicaswrest.Dto.BookingDTO;
import com.practicaswrest.Dto.BookingMapperDto;
import com.practicaswrest.Modelo.Booking;
import com.practicaswrest.Enumeraciones.BookingStatus;
import com.practicaswrest.repo.BookingReposity;
import com.practicaswrest.service.IBooking;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
public class BookingController {


    @Autowired
    private IBooking iBooking;

    @Autowired
    private BookingMapperDto bookingMapperDto;

    @Autowired
    private BookingReposity bookingReposity;


    @GetMapping("/booking/{id}")
    public ResponseEntity<?> buscarReservaporid(@PathVariable  int id){



        if(!iBooking.existereservaporid(id)){
            return new ResponseEntity<>("mensaje: No se encuentra la reserva realizada", HttpStatus.BAD_REQUEST);
        }

        BookingDTO bookingDTO = new BookingDTO();
        BeanUtils.copyProperties(iBooking.findbyid(id).get(),bookingDTO);

        return new ResponseEntity<>(bookingDTO, HttpStatus.OK);
    }


    @GetMapping("/booking/flight/{id}")
    public ResponseEntity<?> listarReservasPorVuelo(@PathVariable int id){
        List<BookingDTO> bookingDTOS = new ArrayList<>();
        if(iBooking.findbyfligth(id).size() == 0){
            return new ResponseEntity<>("mensaje: no hay reservas para ese vuelo, en este momento", HttpStatus.BAD_REQUEST);
        }
        for(int i = 0; i< iBooking.findbyfligth(id).size(); i++ ){
            BookingDTO bookingDTO = bookingMapperDto.toDto(iBooking.findbyfligth(id).get(i));
            bookingDTOS.add(bookingDTO);
        }




        return new ResponseEntity<>(bookingDTOS,HttpStatus.OK);
    }

    @PostMapping("/booking/flight/{idflight}/user/{userid}")
    public ResponseEntity<?> Crear(@RequestBody Booking booking,@PathVariable int idflight,  @PathVariable int userid){

        Long userId = Long.valueOf(userid);
        BookingDTO bookingDTO = new BookingDTO();
        Booking result = iBooking.crear(booking,userId,idflight);
        BeanUtils.copyProperties(result,bookingDTO);
        return new ResponseEntity<>(bookingDTO, HttpStatus.OK);
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
    public ResponseEntity<?> ListarReservas(
            @RequestParam(required = false) BookingStatus bookingStatus,
            @RequestParam(required = false) String nombreUser
    ) {
        List<BookingDTO> result;

        if (bookingStatus == null && nombreUser == null) {
            result = bookingReposity.findAll().stream()
                    .map(bookingMapperDto::toDto)
                    .collect(Collectors.toList());
        } else if (bookingStatus == null && nombreUser != null) {
            List<Booking> bookingsByCustomer = iBooking.findbycustomername(nombreUser);
            result = bookingsByCustomer.stream()
                    .map(bookingMapperDto::toDto)
                    .collect(Collectors.toList());
        } else if (bookingStatus != null && nombreUser == null) {
            result = iBooking.findbystatus(bookingStatus).stream()
                    .map(bookingMapperDto::toDto)
                    .collect(Collectors.toList());
        } else {
            result = iBooking.listarxStatusYcustomer(bookingStatus, nombreUser).stream()
                    .map(bookingMapperDto::toDto)
                    .collect(Collectors.toList());
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }






}

