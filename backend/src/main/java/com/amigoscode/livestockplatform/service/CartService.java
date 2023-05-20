package com.amigoscode.livestockplatform.service;

import com.amigoscode.livestockplatform.entity.CartEntity;
import com.amigoscode.livestockplatform.repository.CartDao;
import com.amigoscodelivestock_platform.model.Cart;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartDao cartDao;

    @Autowired
    public CartService(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    public List<Cart> listCarts() {
        List<CartEntity> cartEntities = cartDao.findAll();
        return cartEntities.stream().map(this::buildCartModel).toList();
    }

    public Optional<Cart> getCart(Long id) {
        Optional<Cart> order = this.listCarts().stream().filter(u -> id == u.getId().intValue()).findFirst();
        return order;
    }

    @Transactional
    public Cart createCart(Cart order) {
        Gson gson = new Gson();
        String tmp = gson.toJson(order);
        CartEntity ordersEntity = cartDao.save(gson.fromJson(tmp, CartEntity.class));
        return buildCartModel(ordersEntity);
    }

    @Transactional
    public Cart updateCart(Long id, Cart order) {
        order.setId(id);
        Gson gson = new Gson();
        String tmp = gson.toJson(order);

        CartEntity ordersEntity = cartDao.save(gson.fromJson(tmp, CartEntity.class));

        return buildCartModel(ordersEntity);
    }

    public void deleteCart(Long id) {
        cartDao.deleteById(id.intValue());
    }

    private Cart buildCartModel(CartEntity ordersEntity) {
        Gson gson = new Gson();
        String tmp = gson.toJson(ordersEntity);
        return gson.fromJson(tmp, Cart.class);
    }
}
