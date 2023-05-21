package com.amigoscode.livestockplatform.service;

import com.amigoscode.livestockplatform.entity.ProductEntity;
import com.amigoscode.livestockplatform.repository.ProductDao;
import com.amigoscodelivestock_platform.model.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
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
        Optional<Product> product = this.listProducts().stream().filter(u -> id == u.getId().intValue()).findFirst();
        return product;
    }

    @Transactional
    public Product createProduct(Product product) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String tmp = gson.toJson(product);
        ProductEntity productsEntity = productDao.save(gson.fromJson(tmp, ProductEntity.class));
        return buildProductModel(productsEntity);
    }

    @Transactional
    public Product updateProduct(Long id, Product product) {
        product.setId(id);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        gson.newBuilder().setPrettyPrinting();
        String tmp = gson.toJson(product);

        ProductEntity productsEntity = productDao.save(gson.fromJson(tmp, ProductEntity.class));

        return buildProductModel(productsEntity);
    }

    public void deleteProduct(Long id) {
        productDao.deleteById(id.intValue());
    }

    private Product buildProductModel(ProductEntity productsEntity) {
        Gson gson = new Gson();
        String tmp = gson.toJson(productsEntity);
        return gson.fromJson(tmp, Product.class);
    }
}
