package com.amigoscode.livestockplatform.controller;

import com.amigoscodelivestock_platform.api.OrdersApi;
import com.amigoscodelivestock_platform.model.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("${openapi.swaggerPetstoreOpenAPI30.base-path:/api/v1}")
public class OrderController implements OrdersApi {

    public ResponseEntity<List<Order>> listOrders() {
        Order order = new Order();
        List<Order> list = new ArrayList<>();
        list.add(order);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
