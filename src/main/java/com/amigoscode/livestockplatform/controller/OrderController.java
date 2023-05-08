package com.amigoscode.livestockplatform.controller;

import com.amigoscode.livestockplatform.service.OrderService;
import com.amigoscodelivestock_platform.api.OrdersApi;
import com.amigoscodelivestock_platform.model.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("${openapi.swaggerPetstoreOpenAPI30.base-path:/api/v1}")
public class OrderController implements OrdersApi {

    private OrderService orderService;

    public ResponseEntity<List<Order>> listOrders() {

        List<Order> list = orderService.listOrders();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    public ResponseEntity<Order> getUser(@PathVariable("orderId") Long orderId) {
        Optional<Order> userOptional = orderService.getOrder(orderId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("No user with id " + orderId + " found.");
        }
        return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);

    }

    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order createdUser = orderService.createOrder(order);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    public ResponseEntity<Order> updateOrder(@PathVariable("orderId") Long orderId, @Valid @RequestBody Order order) {
        Optional<Order> userOptional = orderService.getOrder(orderId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("No user with id " + orderId + " found.");
        }
        Order updatedOrder = orderService.updateOrder(orderId, order);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteOrder(@PathVariable("orderId") Long orderId) {
        int status = getUser(orderId).getStatusCode().value();
        if (HttpStatus.NOT_FOUND.value() == status) {
            throw new IllegalArgumentException("The id user: " + orderId + " does not exist.");
        }
        orderService.deleteOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
