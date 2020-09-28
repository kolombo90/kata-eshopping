package com.onepoint.kata.eshopping.service;

import com.onepoint.kata.eshopping.dto.OrderDto;
import com.onepoint.kata.eshopping.dto.OrderLineDto;
import com.onepoint.kata.eshopping.enums.OrderStatus;
import com.onepoint.kata.eshopping.exception.RessourceNotFoundException;
import com.onepoint.kata.eshopping.model.*;
import com.onepoint.kata.eshopping.repository.BillRepository;
import com.onepoint.kata.eshopping.repository.OrderRepository;
import com.onepoint.kata.eshopping.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    BillRepository billRepository;

    @Autowired
    ProductRepository productRepository;

    @Transactional
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional
    public void addOrder(OrderDto orderDto) throws RessourceNotFoundException {
        float productAmount = 0;
        float totalWeight = 0;
        float shipmentAmount = 0;
        List<OrderLine> orderLineList = new ArrayList<>();

        for (OrderLineDto orderLineDto : orderDto.getOrderLineDtoList()) {
            Product p = productRepository.findById(orderLineDto.getProductId())
                    .orElseThrow(() -> new RessourceNotFoundException("Please provide a valid product id"));
            productAmount += orderLineDto.getQuantity() * p.getPrice();
            totalWeight += orderLineDto.getQuantity() * p.getWeight();
            OrderLine orderLine = OrderLine.builder()
                    .product(p)
                    .quantity(orderLineDto.getQuantity())
                    .build();
            orderLineList.add(orderLine);
        }


        // 25 is the price per 10 kilos of shipment
        shipmentAmount = (float) Math.ceil(totalWeight / 10) * 25;
        Order order = Order.builder().
                status(OrderStatus.valueOf(orderDto.getStatus()))
                .shipmentAmount(shipmentAmount)
                .totalAmount(productAmount + shipmentAmount)
                .totalWeight(totalWeight)
                .orderLineList(new ArrayList<>())
                .build();
        //Adding OrderLine to Order
        for (OrderLine orderLine : orderLineList) {
            order.addOrderLine(orderLine);
        }

        Order savedOrder = orderRepository.save(order);

        if (orderDto.getStatus().equals("PAID")) {
            generateBill(savedOrder);
        }
    }

    public void deleteOrder(Long id) throws RessourceNotFoundException {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Please provide a valid order id"));
        if (order.getStatus().equals(OrderStatus.PAID)) {
            throw new RessourceNotFoundException("you can not delete a paid order");
        }
        orderRepository.delete(order);
    }

    public void generateBill(Order order) {
        Date now = new Date();
        Bill bill = Bill.builder()
                .order(order)
                .creationDate(now)
                .amount(order.getTotalAmount() - calculateReduction(order.getTotalAmount()))
                .build();
        Bill savedBill = billRepository.save(bill);
    }

    public float calculateReduction(float totalAmount) {
        if (totalAmount > 1000) {
            return (totalAmount / 100) * 5;
        }
        return 0f;
    }
}
