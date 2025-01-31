package com.example.magazzino.mapper;

import com.example.magazzino.model.dto.OrderDto;
import com.example.magazzino.model.entity.OrderEntity;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    public static OrderDto mapToOrderDto(OrderEntity orderEntity) {
        OrderDto orderDto = new OrderDto();
        orderDto.setIdOrder(orderEntity.getId());
        orderDto.setOrderName(orderEntity.getOrderName());
        orderDto.setStatus(orderEntity.getStatus());
        orderDto.setTotalPayment(orderEntity.getTotalPayment());
        orderDto.setCustomer(CustomerMapper.mapCustomerToDto(orderEntity.getCustomer()));
        orderDto.setProducts(ProductMapper.mapProductToDto(orderEntity.getProducts()));
        return orderDto;
    }

    public static OrderEntity mapToOrderEntity(OrderDto orderDto) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(orderDto.getIdOrder());
        orderEntity.setOrderName(orderDto.getOrderName());
        orderEntity.setStatus(orderDto.getStatus());
        orderEntity.setTotalPayment(orderDto.getTotalPayment());
        orderEntity.setCustomer(CustomerMapper.mapCustomerToEntity(orderDto.getCustomer()));
        orderEntity.setProducts(ProductMapper.mapProductToEntity(orderDto.getProducts()));
        return orderEntity;
    }

    public static List<OrderDto> mapToOrderDto(List<OrderEntity> orderEntity) {
        List<OrderDto> orderDtoList = new ArrayList<>();

        for(OrderEntity ord : orderEntity) {
            OrderDto orderDto = new OrderDto();
            orderDto = mapToOrderDto(ord);
            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }

    public static List<OrderEntity> mapToOrderEntity(List<OrderDto> orderDto) {
        List<OrderEntity> orderEntityList = new ArrayList<>();

        for(OrderDto ord : orderDto) {
            OrderEntity orderEntity = new OrderEntity();
            orderEntity = mapToOrderEntity(ord);
            orderEntityList.add(orderEntity);
        }
        return orderEntityList;
    }

}
