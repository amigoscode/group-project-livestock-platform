package com.amigoscode.livestockplatform.service;

import com.amigoscode.livestockplatform.entity.OrdersEntity;
import com.amigoscode.livestockplatform.repository.OrderDao;
import com.amigoscodelivestock_platform.model.Order;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderDao orderDao;

    @Autowired
    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public List<Order> listOrders() {
        List<OrdersEntity> ordersEntities = orderDao.findAll();
        return ordersEntities.stream().map(this::buildOrderModel).toList();
    }

    public Optional<Order> getOrder(Long id) {
        Optional<Order> order = this.listOrders().stream().filter(u -> id == u.getId().intValue()).findFirst();
        return order;
    }

    @Transactional
    public Order createOrder(Order order) {
        Gson gson = new Gson();
        String tmp = gson.toJson(order);
        OrdersEntity ordersEntity = orderDao.save(gson.fromJson(tmp, OrdersEntity.class));
        return buildOrderModel(ordersEntity);
    }

    @Transactional
    public Order updateOrder(Long id, Order order) {
        order.setId(id);
        Gson gson = new Gson();
        String tmp = gson.toJson(order);

        OrdersEntity ordersEntity = orderDao.save(gson.fromJson(tmp, OrdersEntity.class));

        return buildOrderModel(ordersEntity);
    }

    public void deleteOrder(Long id) {
        orderDao.deleteById(id.intValue());
    }

    private Order buildOrderModel(OrdersEntity ordersEntity) {
        Gson gson = new Gson();
        String tmp = gson.toJson(ordersEntity);
        return gson.fromJson(tmp, Order.class);
    }
}
