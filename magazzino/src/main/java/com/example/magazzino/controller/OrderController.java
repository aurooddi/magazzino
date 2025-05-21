package com.example.magazzino.controller;

import com.example.magazzino.model.OrderStatus;
import com.example.magazzino.model.dto.CustomerDto;
import com.example.magazzino.model.dto.OrderDto;
import com.example.magazzino.model.entity.OrderEntity;
import com.example.magazzino.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.spi.MidiDeviceProvider;
import java.util.List;

@RestController
@RequestMapping(path = "magazzino/order")
@Tag(name = "Order API", description = "Gestione degli ordini")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @Operation(summary = "Crea l'ordine", description = "Metedo che crea un nuovo ordine")
    public OrderDto registerNewOrder (@RequestBody OrderDto order) {
        return orderService.create(order);
    }

    @DeleteMapping(path = "{idOrder}")
    @Operation(summary = "Elimina ordine", description = "Metodo che elimina un ordine")
    public void deleteOrder (@PathVariable("idOrder") Long idOrder) {
        orderService.delete(idOrder);
    }

    @PutMapping(path = "{idOrder}")
    @Operation(summary = "Aggiorna gli ordini", description = "Metodo che aggiorna un ordine")
    public void updateOrder(
            @PathVariable("idOrder") Long idOrder,
            @RequestParam(required = false) String orderName,
            @RequestParam(required = false) Boolean status) {
        orderService.update(idOrder, orderName, status);
    }

    @GetMapping
    @Operation(summary = "Lista completa", description = "Ritorna la lista di tutti gli ordini")
    public List<OrderDto> getAllOrders() {
        return orderService.getAll();
    }

    @PutMapping(path = "/status/{idOrder}")
    public ResponseEntity<OrderEntity> updateStatusOrder(@PathVariable("idOrder") Long id,
                                                         @RequestParam OrderStatus status) {
        OrderEntity updatedOrderStatus = orderService.updateOrderStatus(id, status);
        return ResponseEntity.ok(updatedOrderStatus);
    }

}
