package com.amigoscode.livestockplatform.controller;

import com.amigoscode.livestockplatform.service.CartService;
import com.amigoscodelivestock_platform.api.CartsApi;
import com.amigoscodelivestock_platform.model.Cart;
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
public class CartController implements CartsApi {

    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    public ResponseEntity<List<Cart>> listCarts() {

        List<Cart> list = cartService.listCarts();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    public ResponseEntity<Cart> getCart(@PathVariable("cartId") Long cartId) {
        Optional<Cart> userOptional = cartService.getCart(cartId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("No cart with id " + cartId + " found.");
        }
        return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);

    }

    public ResponseEntity<Cart> createCart(@RequestBody Cart order) {
        Cart createdUser = cartService.createCart(order);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    public ResponseEntity<Cart> updateCart(@PathVariable("cartId") Long cartId, @Valid @RequestBody Cart order) {
        Optional<Cart> userOptional = cartService.getCart(cartId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("No cart with id " + cartId + " found.");
        }
        Cart updatedCart = cartService.updateCart(cartId, order);
        return new ResponseEntity<>(updatedCart, HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteCart(@PathVariable("cartId") Long cartId) {
        int status = getCart(cartId).getStatusCode().value();
        if (HttpStatus.NOT_FOUND.value() == status) {
            throw new IllegalArgumentException("The id cart: " + cartId + " does not exist.");
        }
        cartService.deleteCart(cartId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
