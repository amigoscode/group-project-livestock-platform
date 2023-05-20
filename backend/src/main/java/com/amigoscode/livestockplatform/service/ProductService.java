package com.amigoscode.livestockplatform.service;

import com.amigoscode.livestockplatform.entity.ProductEntity;
import com.amigoscode.livestockplatform.repository.ProductDao;
import com.amigoscodelivestock_platform.model.Product;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductDao productDao;

    @Autowired
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> listProducts() {
        List<ProductEntity> cartEntities = productDao.findAll();
        return cartEntities.stream().map(this::buildProductModel).toList();
    }

    public Optional<Product> getProduct(Long id) {
        Optional<Product> order = this.listProducts().stream().filter(u -> id == u.getId().intValue()).findFirst();
        return order;
    }

    @Transactional
    public Product createProduct(Product order) {
        Gson gson = new Gson();
        String tmp = gson.toJson(order);
        ProductEntity ordersEntity = productDao.save(gson.fromJson(tmp, ProductEntity.class));
        return buildProductModel(ordersEntity);
    }

    @Transactional
    public Product updateProduct(Long id, Product order) {
        order.setId(id);
        Gson gson = new Gson();
        String tmp = gson.toJson(order);

        ProductEntity ordersEntity = productDao.save(gson.fromJson(tmp, ProductEntity.class));

        return buildProductModel(ordersEntity);
    }

    public void deleteProduct(Long id) {
        productDao.deleteById(id.intValue());
    }

    private Product buildProductModel(ProductEntity ordersEntity) {
        Gson gson = new Gson();
        String tmp = gson.toJson(ordersEntity);
        return gson.fromJson(tmp, Product.class);
    }
}
