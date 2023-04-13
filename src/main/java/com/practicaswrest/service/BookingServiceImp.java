package com.practicaswrest.service;

import com.practicaswrest.Modelo.Booking;
import Enumeraciones.BookingStatus;
import com.practicaswrest.Modelo.Flight;
import com.practicaswrest.Modelo.User;
import com.practicaswrest.repo.BookingReposity;
import com.practicaswrest.repo.FlightReposity;
import com.practicaswrest.repo.UserReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImp implements IBooking{


    @Autowired
    private BookingReposity br;

    @Autowired
    private UserReposity ur;

    @Autowired
    private FlightReposity fr;


    @Override
    public Optional<Booking> findbyid(int id) {
             return br.findById(id);
    }

    @Override
    public List<Booking> findbystatus(BookingStatus status) {
        List<Booking> reservas = br.findAll();
        List<Booking> reservaxstatus = new ArrayList<>();
        for (Booking reserva : reservas) {
            if (status == reserva.getStatus()) {
                reservaxstatus.add(reserva);
            }
        }

        return reservaxstatus;


    }

    @Override
    public List<Booking> findbycustomer(int id) {
        List<Booking> reservas = br.findAll();
        List<Booking> reservaxCliente = new ArrayList<>();

        for (Booking reserva : reservas) {
            if (id == reserva.getUsuarios().getId()) {
                reservaxCliente.add(reserva);
            }
        }

        return reservaxCliente;
    }

    @Override
    public List<Booking> Listarvuelos() {
        return br.findAll();
    }

    @Override
    public List<Booking> listarxStatusYcustomer(BookingStatus status, int id) {
        List<Booking> Listastatus = findbystatus(status);
        List<Booking> result = new ArrayList<>();

        for (Booking listastatus : Listastatus) {
            if (listastatus.getUsuarios().getId() == id) {
                result.add(listastatus);
            }
        }
        return result;
    }

    @Override
    public Booking crear(Booking booking, int userid, int id_vuelo) {


        List<User> aux = ur.findAll();
        for (User user : aux) {
            if (user.getId() == userid) {
                booking.setUsuarios(user);
            }
        }
        Optional<Flight> opt2 = fr.findById(id_vuelo);
        booking.setFlight(opt2.get());
        return br.save(booking);


    }

    @Override
    public boolean existeReserva(int id) {
        return br.existsById(id);
    }

    @Override
    public boolean comprobarUsuario(int id) {
        return ur.existsById(id);
    }

    @Override
    public boolean comprobarvuel(int id) {
        return fr.existsById(id);
    }

    @Override
    public void eliminar(int id) {
      br.deleteById(id);
    }

    @Override
    public boolean existereservaporid(int id) {
        return br.existsById(id);
    }




    @Override
    public List<Booking> findbyfligth(int id_vuelo) {
        List<Booking> reservas = br.findAll();
        List<Booking> reservasXvuelo = new ArrayList<>();

        for (Booking reserva : reservas) {
            if (id_vuelo == reserva.getFlight().getId()) {
                reservasXvuelo.add(reserva);
            }
        }

        return reservasXvuelo;

    }
}
