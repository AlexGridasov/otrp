package com.gri.alex.booking.domain.repository;

import com.gri.alex.booking.common.BookingNotFoundException;
import com.gri.alex.booking.domain.model.entity.Booking;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.AbstractMap.SimpleEntry;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.stream.Collectors.toList;

@Repository("bookingRepository")
public class InMemBookingRepository implements BookingRepository<Booking, String> {

    private static final Map<String, Booking> entities;

    static {
        entities = new ConcurrentHashMap<>(Map.ofEntries(
                new SimpleEntry<>("1",
                        new Booking("1", "Booking 1", "1", "1", "1", LocalDate.now(), LocalTime.now())),
                new SimpleEntry<>("2",
                        new Booking("2", "Booking 2", "2", "2", "2", LocalDate.now(), LocalTime.now()))
        ));
    }

    /**
     * Check if given booking name already exist.
     *
     * @param name
     * @return true if already exist, else false
     */
    @Override
    public boolean containsName(String name) {
        try {
            return !findByName(name).isEmpty();
        } catch (BookingNotFoundException ex) {
            return false;
        } catch (Exception ex) {
            //Exception Handler
        }
        return false;
    }

    @Override
    public void add(Booking entity) {
        entities.put(entity.getId(), entity);
    }

    @Override
    public void remove(String id) {
        entities.remove(id);
    }

    @Override
    public void update(Booking entity) {
        if (entities.containsKey(entity.getId())) {
            entities.put(entity.getId(), entity);
        }
    }

    @Override
    public boolean contains(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Booking get(String id) {
        return entities.get(id);
    }

    @Override
    public Collection<Booking> getAll() {
        return entities.values();
    }

    @Override
    public Collection<Booking> findByName(String name) throws BookingNotFoundException {
        int noOfChars = name.length();
        Collection<Booking> bookings = entities.entrySet().stream()
                .filter(b -> b.getValue().getName().toLowerCase()
                        .contains(name.toLowerCase().subSequence(0, noOfChars)))
                .collect(toList())
                .stream()
                .map(k -> k.getValue())
                .collect(toList());

        if (bookings.isEmpty()) {
            Object[] args = {name};
            throw new BookingNotFoundException("bookingNotFound", args);
        }
        return bookings;
    }

}
