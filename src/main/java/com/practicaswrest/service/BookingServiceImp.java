package com.practicaswrest.service;

import com.practicaswrest.Dto.BookingDTO;
import com.practicaswrest.Dto.BookingMapperDto;
import com.practicaswrest.Modelo.Booking;
import com.practicaswrest.Enumeraciones.BookingStatus;
import com.practicaswrest.Modelo.Flight;
import com.practicaswrest.Modelo.Usuario;
import com.practicaswrest.repo.BookingReposity;
import com.practicaswrest.repo.FlightReposity;
import com.practicaswrest.repo.UserReposity;
import org.springframework.beans.BeanUtils;
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
    public List<Booking> findbycustomername(String name) {
        List<Booking> reservas = br.findAll();
        List<Booking> reservaxCliente = new ArrayList<>();
        Long id = findiduserxname(name);

        for (Booking reserva : reservas) {
             if (reserva.getUsuarios().getId() == id){
                 reservaxCliente.add(reserva);
             }
        }

        return reservaxCliente;
    }

    @Override
    public Long findiduserxname(String name) {
        List<Booking> reservas = br.findAll();
        for(Booking reserva : reservas){
            if(reserva.getUsuarios().getNombre().equals(name)){
                return reserva.getUsuarios().getId();
            }
        }

        return null;
    }


    @Override
    public List<Booking> Listarvuelos() {
        return br.findAll();
    }

    @Override
    public List<Booking> listarxStatusYcustomer(BookingStatus status, String name) {
        List<Booking> Listastatus = findbystatus(status);
        List<Booking> result = new ArrayList<>();
        Long id = findiduserxname(name);

        for (Booking listastatus : Listastatus) {
            if (listastatus.getUsuarios().getId() == id) {
                result.add(listastatus);
            }
        }


        return result;
    }

    @Override
    public Booking crear(Booking booking, Long userid, int id_vuelo) {


        List<Usuario> aux = ur.findAll();
        for (Usuario user : aux) {
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
    public boolean comprobarUsuario(Long id) {
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
        List<BookingDTO> bookingDTOList = new ArrayList<>();
        BookingDTO dto = new BookingDTO();

        for (Booking reserva : reservas) {
            if (id_vuelo == reserva.getFlight().getId()) {
                reservasXvuelo.add(reserva);
            }
        }

        for (Booking reserva: reservasXvuelo){
            bookingDTOList.add(dto);
        }



      return reservasXvuelo;

    }
}
