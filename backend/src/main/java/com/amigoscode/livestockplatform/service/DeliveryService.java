package com.amigoscode.livestockplatform.service;

import com.amigoscode.livestockplatform.entity.DeliveryEntity;
import com.amigoscode.livestockplatform.repository.DeliveryDao;
import com.amigoscodelivestock_platform.model.Delivery;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryService {

    private final DeliveryDao deliveryDao;

    @Autowired
    public DeliveryService(DeliveryDao deliveryDao) {
        this.deliveryDao = deliveryDao;
    }

    public List<Delivery> listDeliveries() {
        List<DeliveryEntity> deliveryEntities = deliveryDao.findAll();
        return deliveryEntities.stream().map(this::buildDeliveryModel).toList();
    }

    public Optional<Delivery> getDelivery(Long id) {
        Optional<Delivery> order = this.listDeliveries().stream().filter(u -> id == u.getId().intValue()).findFirst();
        return order;
    }

    @Transactional
    public Delivery createDelivery(Delivery order) {
        Gson gson = new Gson();
        String tmp = gson.toJson(order);
        DeliveryEntity ordersEntity = deliveryDao.save(gson.fromJson(tmp, DeliveryEntity.class));
        return buildDeliveryModel(ordersEntity);
    }

    @Transactional
    public Delivery updateDelivery(Long id, Delivery order) {
        order.setId(id);
        Gson gson = new Gson();
        String tmp = gson.toJson(order);

        DeliveryEntity ordersEntity = deliveryDao.save(gson.fromJson(tmp, DeliveryEntity.class));

        return buildDeliveryModel(ordersEntity);
    }

    public void deleteDelivery(Long id) {
        deliveryDao.deleteById(id.intValue());
    }

    private Delivery buildDeliveryModel(DeliveryEntity ordersEntity) {
        Gson gson = new Gson();
        String tmp = gson.toJson(ordersEntity);
        return gson.fromJson(tmp, Delivery.class);
    }
}
