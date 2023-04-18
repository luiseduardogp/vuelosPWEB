package com.practicaswrest.Dto;

import com.practicaswrest.Modelo.Booking;
import org.springframework.stereotype.Component;

@Component
public class BookingMapperDto {

    public BookingDTO toDto(Booking booking){
        BookingDTO dto= new BookingDTO();
        dto.setStatus(booking.getStatus());
        dto.setId(booking.getId());
        dto.setPaymentToken(booking.getPaymentToken());
        dto.setCheckedIn(booking.isCheckedIn());
        dto.setCreatedAt(booking.getCreatedAt());
        dto.setBookingReference(booking.getBookingReference());

        return dto;
    }
}
