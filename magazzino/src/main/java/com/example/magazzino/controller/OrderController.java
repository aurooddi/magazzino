package com.example.magazzino.controller;

import com.example.magazzino.model.dto.CustomerDto;
import com.example.magazzino.model.dto.OrderDto;
import com.example.magazzino.model.entity.OrderEntity;
import com.example.magazzino.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.spi.MidiDeviceProvider;
import java.util.List;

@RestController
@RequestMapping(path = "magazzino/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public OrderEntity registerNewOrder (@RequestBody OrderDto order) {
        return orderService.create(order);
    }

    @DeleteMapping(path = "{idOrder}")
    public void deleteOrder (@PathVariable("idOrder") Long idOrder) {
        orderService.delete(idOrder);
    }

    @PutMapping(path = "{idOrder}")
    public void updateOrder(
            @PathVariable("idOrder") Long idOrder,
            @RequestParam(required = false) String orderName,
            @RequestParam(required = false) Boolean status) {
        orderService.update(idOrder, orderName, status);
    }

    @GetMapping
    public List<OrderDto> getAllOrders() {
        return orderService.getAll();
    }

}
