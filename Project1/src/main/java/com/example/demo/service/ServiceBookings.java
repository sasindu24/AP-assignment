package com.example.demo.service;

import com.example.demo.model.Orders;
import com.example.demo.repo.BookingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceBookings {
    @Autowired
    BookingsRepository bookingsRepository;

    public List<Orders> viewAllBookings() {
        return bookingsRepository.findAll();
    }
    public void addBookings(Orders orders) {
        bookingsRepository.save(orders);
    }

    public void deleteBookings(int id) {
        bookingsRepository.deleteById(id);
    }

    public Optional<Orders> getBookingsById(int id) {
        return bookingsRepository.findById(id);
    }

    public List<Orders> lisAllBookings(String keyword) {
        if (keyword != null) {
            return bookingsRepository.search(keyword);
        }else {
            return viewAllBookings();
        }
    }

    public List<Orders> lisAllDriverBookings(String keyword) {
        if (keyword != null) {
            return bookingsRepository.searchDriver(keyword);
        }else {
            return viewAllBookings();
        }
    }
}
