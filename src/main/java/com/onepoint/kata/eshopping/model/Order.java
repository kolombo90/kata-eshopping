package com.onepoint.kata.eshopping.model;

import com.onepoint.kata.eshopping.enums.OrderStatus;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@ToString
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "status")
    private OrderStatus status;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderLine> orderLineList;

    @Column(name = "shipment_amount")
    private float shipmentAmount;

    @Column(name = "total_amount")
    private float totalAmount;

    @Column(name = "total_weight")
    private float totalWeight;

    public void addOrderLine(OrderLine orderLine) {
        orderLineList.add(orderLine);
        orderLine.setOrder(this);
    }
}
