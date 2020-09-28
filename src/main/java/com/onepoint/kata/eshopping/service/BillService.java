package com.onepoint.kata.eshopping.service;

import com.onepoint.kata.eshopping.model.Bill;
import com.onepoint.kata.eshopping.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {

    @Autowired
    BillRepository billRepository;

    public List<Bill> sortBills(String field){
        return billRepository.findAll(Sort.by(Sort.Direction.ASC, field));
    }
}
