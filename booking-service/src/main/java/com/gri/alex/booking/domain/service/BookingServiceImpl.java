package com.gri.alex.booking.domain.service;

import com.gri.alex.booking.common.DuplicateBookingException;
import com.gri.alex.booking.common.InvalidBookingException;
import com.gri.alex.booking.domain.model.entity.Booking;
import com.gri.alex.booking.domain.model.entity.Entity;
import com.gri.alex.booking.domain.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Service("bookingService")
public class BookingServiceImpl extends BaseService<Booking, String>
        implements BookingService {

    private final BookingRepository<Booking, String> bookingRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository<Booking, String> bookingRepository) {
        super(bookingRepository);
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void add(Booking booking) throws Exception {
        if (bookingRepository.containsName(booking.getName())) {
            Object[] args = {booking.getName()};
            throw new DuplicateBookingException("duplicateBooking", args);
        }

        if (booking.getName() == null || booking.getName().isEmpty()) {
            Object[] args = {"Booking with null or empty name"};
            throw new InvalidBookingException("invalidBooking", args);
        }
        super.add(booking);
    }

    @Override
    public Collection<Booking> findByName(String name) throws Exception {
        return bookingRepository.findByName(name);
    }

    @Override
    public void update(Booking booking) {
        bookingRepository.update(booking);
    }

    @Override
    public void delete(String id) {
        bookingRepository.remove(id);
    }

    @Override
    public Entity<String> findById(String id) {
        return bookingRepository.get(id);
    }

    @Override
    public Collection<Booking> findByCriteria(Map<String, ArrayList<String>> name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
