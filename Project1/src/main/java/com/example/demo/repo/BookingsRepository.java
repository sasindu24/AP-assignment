package com.example.demo.repo;

import com.example.demo.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingsRepository extends JpaRepository<Orders, Integer> {
    @Query(value = "SELECT * FROM booking WHERE customer LIKE %?1%", nativeQuery = true)
    List<Orders> search(String keyword);

    @Query(value = "SELECT * FROM booking WHERE driver_name LIKE %?1%", nativeQuery = true)
    List<Orders> searchDriver(String keyword);
}
