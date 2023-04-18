package com.practicaswrest.Dto;

import com.practicaswrest.Enumeraciones.BookingStatus;
import com.practicaswrest.Modelo.Flight;
import com.practicaswrest.Modelo.Usuario;

public class BookingDTO {

    private int id;

    private BookingStatus status;


    private String paymentToken;


    private boolean checkedIn;


    private String createdAt;


    private String bookingReference;



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
}
