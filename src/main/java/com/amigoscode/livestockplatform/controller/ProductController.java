package com.amigoscode.livestockplatform.controller;

import com.amigoscode.livestockplatform.service.ProductService;
import com.amigoscodelivestock_platform.api.ProductsApi;
import com.amigoscodelivestock_platform.model.Product;
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
public class ProductController implements ProductsApi {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public ResponseEntity<List<Product>> listProducts() {

        List<Product> list = productService.listProducts();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    public ResponseEntity<Product> getProduct(@PathVariable("productId") Long productId) {
        Optional<Product> userOptional = productService.getProduct(productId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("No product with id " + productId + " found.");
        }
        return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);

    }

    public ResponseEntity<Product> createProduct(@RequestBody Product order) {
        Product createdUser = productService.createProduct(order);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    public ResponseEntity<Product> updateProduct(@PathVariable("productId") Long productId,
            @Valid @RequestBody Product order) {
        Optional<Product> userOptional = productService.getProduct(productId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("No product with id " + productId + " found.");
        }
        Product updatedProduct = productService.updateProduct(productId, order);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteProduct(@PathVariable("productId") Long productId) {
        int status = getProduct(productId).getStatusCode().value();
        if (HttpStatus.NOT_FOUND.value() == status) {
            throw new IllegalArgumentException("The id product: " + productId + " does not exist.");
        }
        productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
