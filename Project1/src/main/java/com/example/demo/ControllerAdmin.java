package com.example.demo;

import com.example.demo.dataTransfer.DTODriver;
import com.example.demo.model.Driver;
import com.example.demo.model.Orders;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repo.RoleRepository;
import com.example.demo.repo.UserRepository;
import com.example.demo.service.ServiceBookings;
import com.example.demo.service.ServiceDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ControllerAdmin {


    @Autowired
    ServiceDriver serviceDriver;

    @RequestMapping("/admin/vehicles")
    public String manageDrivers(Model model, @Param("keyword") String keyword) {
        List<Driver> listDriver = serviceDriver.lisAll(keyword);
        model.addAttribute("drivers", listDriver);
        model.addAttribute("keyword", keyword);
        return "manageDrivers";
    }

    @GetMapping("/admin/driver/add")
    public String getAddDriver(Model model) {
        model.addAttribute("DTODriver", new DTODriver());
        return "addDrivers";
    }

    @Autowired
    ServiceBookings serviceBookings;

    @GetMapping("/admin/bookings")
    public String bookings(Model model) {
        List<Orders> orders = serviceBookings.viewAllBookings();
        model.addAttribute("orders", orders);
        return "adminViewBookings";
    }

    @GetMapping("/admin/registerDriver")
    public String registerDriver(Model model) {
        return "driverRegistration";
    }

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;
    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/images/vehicleImages";

    @PostMapping("/admin/registerDriver")
    public String registerPost(@ModelAttribute("user") User user, HttpServletRequest request) throws ServletException {
        String password = user.getPassword();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findById(3).get());
        user.setRoles(roles);
        userRepository.save(user);
        return "redirect:/admin";
    }

    @PostMapping("/admin/driver/add")
    public String driverAdd(@ModelAttribute("DTODriver") DTODriver DTODriver,
                            @RequestParam("productImage")MultipartFile file,
                            @RequestParam("imgName")String imgName) throws IOException {
        Driver driver = new Driver();
        driver.setId(DTODriver.getId());
        driver.setDriver_id(DTODriver.getDriver_id());
        driver.setBranch(DTODriver.getBranch());
        driver.setVehicle_id(DTODriver.getVehicle_id());
        driver.setVehicle_type(DTODriver.getVehicle_type());
        driver.setAddress(DTODriver.getAddress());
        driver.setMobileNumber(DTODriver.getMobileNumber());
        String imageUUID;
        if (!file.isEmpty()) {
            imageUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
            Files.write(fileNameAndPath, file.getBytes());
        } else {
            imageUUID = imgName;
        }
        driver.setImageName(imageUUID);
        serviceDriver.addDriver(driver);
        return "redirect:/admin/vehicles";
    }

    @GetMapping("/admin/driver/delete/{id}")
    public String getDeleteDriver(@PathVariable int id) {
        serviceDriver.deleteDriver(id);
        return "redirect:/admin/vehicles";
    }

    @GetMapping("/admin/bookings/delete/{id}")
    public String getDeleteBookings(@PathVariable int id) {
        serviceBookings.deleteBookings(id);
        return "redirect:/admin/bookings";
    }

    @GetMapping("/admin/driver/update/{id}")
    public String getUpdateDriver(@PathVariable int id, Model model) {
        Driver driver = serviceDriver.getDriverById(id).get();
        DTODriver DTODriver = new DTODriver();
        DTODriver.setId(driver.getId());
        DTODriver.setDriver_id(driver.getDriver_id());
        DTODriver.setBranch(driver.getBranch());
        DTODriver.setVehicle_id(driver.getVehicle_id());
        DTODriver.setVehicle_type(driver.getVehicle_type());
        DTODriver.setAddress(driver.getAddress());
        DTODriver.setMobileNumber(driver.getMobileNumber());
        DTODriver.setImageName(driver.getImageName());

        model.addAttribute("DTODriver", DTODriver);

        return "updateDriver";
    }

    @GetMapping("/admin")
    public String adminHome() {
        return "adminPage";
    }
}
