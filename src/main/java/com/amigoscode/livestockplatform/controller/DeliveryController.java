package com.amigoscode.livestockplatform.controller;

import com.amigoscode.livestockplatform.service.DeliveryService;
import com.amigoscodelivestock_platform.api.DeliveriesApi;
import com.amigoscodelivestock_platform.model.Delivery;
import com.amigoscodelivestock_platform.model.Delivery;
import org.springframework.beans.factory.annotation.Autowired;
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
public class DeliveryController implements DeliveriesApi {

    private DeliveryService deliveryService;

    @Autowired
    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    public ResponseEntity<List<Delivery>> listDeliveries() {

        List<Delivery> list = deliveryService.listDeliveries();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    public ResponseEntity<Delivery> getDelivery(@PathVariable("deliveryId") Long deliveryId) {
        Optional<Delivery> userOptional = deliveryService.getDelivery(deliveryId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("No user with id " + deliveryId + " found.");
        }
        return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);

    }

    public ResponseEntity<Delivery> createDelivery(@RequestBody Delivery order) {
        Delivery createdUser = deliveryService.createDelivery(order);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    public ResponseEntity<Delivery> updateDelivery(@PathVariable("deliveryId") Long deliveryId,
            @Valid @RequestBody Delivery order) {
        Optional<Delivery> userOptional = deliveryService.getDelivery(deliveryId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("No user with id " + deliveryId + " found.");
        }
        Delivery updatedDelivery = deliveryService.updateDelivery(deliveryId, order);
        return new ResponseEntity<>(updatedDelivery, HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteDelivery(@PathVariable("deliveryId") Long deliveryId) {
        int status = getDelivery(deliveryId).getStatusCode().value();
        if (HttpStatus.NOT_FOUND.value() == status) {
            throw new IllegalArgumentException("The id user: " + deliveryId + " does not exist.");
        }
        deliveryService.deleteDelivery(deliveryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
