package com.practicaswrest.service;

import com.practicaswrest.Dto.MostrarVueloDto;
import com.practicaswrest.Modelo.Flight;
import com.practicaswrest.Modelo.Usuario;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IFlight {



    List<MostrarVueloDto> listarporparams(String departureAirportCode, String arrivalAirportCode, String departureDate);


     Flight crear(Flight flight);

     Flight actualizar(int id, Flight flight);

     void eliminar(int id);

     List<Flight> listarVuelos();


     List<Flight> listarXairportCode(String airportCode, String fecha);

     Boolean existeVueloxid(int id);




}
