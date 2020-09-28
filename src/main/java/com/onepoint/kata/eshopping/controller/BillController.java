package com.onepoint.kata.eshopping.controller;

import com.onepoint.kata.eshopping.exception.RessourceNotFoundException;
import com.onepoint.kata.eshopping.model.Bill;
import com.onepoint.kata.eshopping.service.BillService;
import com.onepoint.kata.eshopping.utilities.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class BillController {

    @Autowired
    BillService billService;

    @GetMapping("/api/bill/sort/{field}")
    public List<Bill> sortProducts(@PathVariable String field) throws  RessourceNotFoundException {
        Set<String> sourceFieldList = MyUtils.getAllFields(Bill.class);
        if(!sourceFieldList.contains(field)){
            throw new RessourceNotFoundException("sort field does not exist");
        }
        return billService.sortBills(field);
    }
}
