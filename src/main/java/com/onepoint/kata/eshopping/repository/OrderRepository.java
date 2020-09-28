package com.onepoint.kata.eshopping.repository;

import com.onepoint.kata.eshopping.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
