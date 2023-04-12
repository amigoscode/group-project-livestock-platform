package com.amigoscode.livestockplatform.controller;

import com.amigoscodelivestock_platform.api.DeliveriesApi;
import com.amigoscodelivestock_platform.model.Delivery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("${openapi.swaggerPetstoreOpenAPI30.base-path:/api/v1}")
public class DeliveryController implements DeliveriesApi {

    public ResponseEntity<List<Delivery>> listDeliveries() {
        Delivery delivery = new Delivery();
        List<Delivery> list = new ArrayList<>();
        list.add(delivery);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
