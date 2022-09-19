package com.example.demo;

import com.example.demo.model.Orders;
import com.example.demo.repo.BookingsRepository;
import com.example.demo.service.ServiceBookings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ControllerDriver {
    @Autowired
    ServiceBookings serviceBookings;
    @Autowired
    BookingsRepository bookingsRepository;

    @GetMapping("/driver")
    public String driverHome() {
        return "driverPage";
    }

    @GetMapping("/driver/viewBookings")
    public String bookings(Model model,  @Param("keyword") String keyword) {
        List<Orders> orders = serviceBookings.lisAllDriverBookings(keyword);
        model.addAttribute("orders", orders);
        model.addAttribute("keyword", keyword);
        return "driverViewBookings";
    }

    @PostMapping("/driver/updateBooking/{id}")
    public String addBooking(@ModelAttribute("book") Orders orders, HttpServletRequest request) throws ServletException {
        bookingsRepository.save(orders);
        return "redirect:/driver";
    }
}
