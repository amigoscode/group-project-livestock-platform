package com.amigoscode.livestockplatform.controller;

import com.amigoscodelivestock_platform.api.ProductsApi;
import com.amigoscodelivestock_platform.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("${openapi.swaggerPetstoreOpenAPI30.base-path:/api/v1}")
public class ProductController implements ProductsApi {
    
    public ResponseEntity<List<Product>> listProducts() {
        Product product = new Product();
        List<Product> list = new ArrayList<>();
        list.add(product);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
