package com.example.demo;

import com.example.demo.model.Driver;
import com.example.demo.model.Orders;
import com.example.demo.repo.BookingsRepository;
import com.example.demo.service.ServiceBookings;
import com.example.demo.service.ServiceDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ControllerHome {
    @Autowired
    ServiceDriver serviceDriver;
    @Autowired
    BookingsRepository bookingsRepository;
    @Autowired
    ServiceBookings serviceBookings;

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        return "home";
    }

    @RequestMapping("/viewVehicles")
    public String manageDrivers(Model model, @Param("keyword1") String keyword) {
        List<Driver> listDriver = serviceDriver.lisAll(keyword);
        model.addAttribute("products", listDriver);
        model.addAttribute("keyword1", keyword);
        return "viewVehicles";
    }

    @GetMapping("/shop/viewproduct/{id}")
    public String viewVehicle(Model model, @PathVariable int id) {
        model.addAttribute("product", serviceDriver.getDriverById(id).get());
        return "vehicleById";
    }

    @PostMapping("/shop/viewproduct/{id}")
    public String addBooking(@ModelAttribute("book") Orders orders, HttpServletRequest request) throws ServletException {
        bookingsRepository.save(orders);
        return "redirect:/viewVehicles";
    }

    @GetMapping("/shop/bookings")
    public String viewBookings(Model model, @Param("keyword") String keyword) {
        List<Orders> orders = serviceBookings.lisAllBookings(keyword);
        model.addAttribute("order", orders);
        model.addAttribute("keyword", keyword);
        return "viewBookings";
    }
}
