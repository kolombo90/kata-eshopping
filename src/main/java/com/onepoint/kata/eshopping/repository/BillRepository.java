package com.onepoint.kata.eshopping.repository;

import com.onepoint.kata.eshopping.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
}
