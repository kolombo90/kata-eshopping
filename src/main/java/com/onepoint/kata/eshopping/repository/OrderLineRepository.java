package com.onepoint.kata.eshopping.repository;

import com.onepoint.kata.eshopping.model.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
}
