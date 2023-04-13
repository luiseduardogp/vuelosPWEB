package com.practicaswrest.Modelo;

import com.practicaswrest.Enumeraciones.BookingStatus;

import javax.persistence.*;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private BookingStatus status;

    @Column
    private String paymentToken;

    @Column
    private boolean checkedIn;

    @Column
    private String createdAt;

    @Column
    private String bookingReference;

    @ManyToOne
    @JoinColumn(name = "usuarioID", referencedColumnName = "id")
    private Usuario usuarios;

    @ManyToOne
    @JoinColumn(name = "outboundFlight", referencedColumnName = "idf")
    private Flight flight;


    public Booking() {
    }


    public Booking(BookingStatus status, String paymentToken, boolean checkedIn, String createdAt, String bookingReference, Usuario usuarios, Flight flight) {
        this.status = status;
        this.paymentToken = paymentToken;
        this.checkedIn = checkedIn;
        this.createdAt = createdAt;
        this.bookingReference = bookingReference;
        this.usuarios = usuarios;
        this.flight = flight;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public String getPaymentToken() {
        return paymentToken;
    }

    public void setPaymentToken(String paymentToken) {
        this.paymentToken = paymentToken;
    }

    public boolean isCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(boolean checkedIn) {
        this.checkedIn = checkedIn;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getBookingReference() {
        return bookingReference;
    }

    public void setBookingReference(String bookingReference) {
        this.bookingReference = bookingReference;
    }

    public Usuario getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuario usuarios) {
        this.usuarios = usuarios;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}
