package com.practicaswrest.service;

import com.practicaswrest.Modelo.Flight;
import com.practicaswrest.repo.FlightReposity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImp implements IFlight{


    @Autowired
    private FlightReposity flightReposity;


    @Override
    public List<Flight> listarporparams(String departureAirportCode, String arrivalAirportCode, String departureDate) {
         List<Flight> vuelos = flightReposity.findAll();
         List<Flight> vuelosxparam = new ArrayList<>();

         for(Flight flight: vuelos){
             if(flight.getArrivalAirportCode().equals(arrivalAirportCode)
                     &&
                     flight.getDepartureAirportCode().equals(departureAirportCode)
                     &&
                     flight.getDepartureDate().equals(departureDate)){
                        vuelosxparam.add(flight);
             }
         }

         return vuelosxparam;
    }

    @Override
    public Flight crear(Flight flight) {
        return flightReposity.save(flight);
    }

    @Override
    public Flight actualizar(int id, Flight flight) {
        Optional<Flight> optionalFlight = flightReposity.findById(id);
        BeanUtils.copyProperties(flight,optionalFlight.get());

        return flightReposity.save(optionalFlight.get());
    }

    @Override
    public void eliminar(int id) {
         flightReposity.deleteById(id);
    }

    @Override
    public List<Flight> listarXairportCode(String airportCode, String fecha) {
        List<Flight> vuelos = flightReposity.findAll();
        List<Flight> vuelosxfecha = new ArrayList<>();

        for(Flight flight: vuelos){
            if(flight.getArrivalAirportCode().equals(airportCode) && flight.getDepartureDate().equals(fecha)){
                vuelosxfecha.add(flight);
            }
        }

        return vuelosxfecha;

    }

    @Override
    public Boolean existeVueloxid(int id) {
        return flightReposity.existsById(id);
    }

    @Override
    public Optional<Flight> vueloxid(int id) {

        Optional<Flight> opt = flightReposity.findById(id);
        return opt;
    }
}
