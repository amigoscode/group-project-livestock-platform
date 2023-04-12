package com.amigoscode.livestockplatform.controller;

import com.amigoscodelivestock_platform.api.CartsApi;
import com.amigoscodelivestock_platform.model.Cart;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("${openapi.swaggerPetstoreOpenAPI30.base-path:/api/v1}")
public class CartController implements CartsApi {

    public ResponseEntity<List<Cart>> listCarts() {
        Cart cart = new Cart();
        List<Cart> list = new ArrayList<>();
        list.add(cart);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
