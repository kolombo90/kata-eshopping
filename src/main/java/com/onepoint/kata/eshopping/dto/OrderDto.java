package com.onepoint.kata.eshopping.dto;

import com.onepoint.kata.eshopping.enums.OrderStatus;
import com.onepoint.kata.eshopping.validator.ValueOfEnum;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Data
public class OrderDto {

    @Valid
    @NotNull(message = "An Order Must have at least one product")
    List<OrderLineDto> orderLineDtoList;

    @ValueOfEnum(enumClass = OrderStatus.class, message = "status should be paid ,cancelled or pending")
    private String status;
}
