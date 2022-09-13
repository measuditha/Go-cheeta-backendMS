package com.carbooking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carbooking.model.BookingDto;
import com.carbooking.repo.Booking;
import com.carbooking.repo.BookingRepository;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    public void confirmBooking(BookingDto bookingDto) {
        Booking booking = getBooking(bookingDto);
        bookingRepository.save(booking);
    }
    public List<Booking> getCustomerBooking(String custId) {
    	return bookingRepository.findAllByCustomerId(custId);
    }
    public List<Booking> getDriverBooking(String driverId) {
    	
    	return bookingRepository.findAllByDriverId(driverId);
    }
    public Booking getBooking(BookingDto bookingDto){
        Booking booking = new Booking();
        booking.setCarId(bookingDto.getCarId());
        booking.setCustomerId(bookingDto.getCustomerId());
        booking.setDriverId(bookingDto.getDriverId());
        booking.setStartDate(bookingDto.getStartDate());
        booking.setEndDate(bookingDto.getEndDate());

        return booking;
    }
}
