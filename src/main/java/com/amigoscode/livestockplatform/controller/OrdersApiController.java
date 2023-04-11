package com.amigoscode.livestockplatform.controller;

import com.amigoscodelivestock_platform.api.OrdersApi;
import com.amigoscodelivestock_platform.api.OrdersApiDelegate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-04-09T17:10:08.534310300+02:00[Europe/Paris]")
@Controller
@RequestMapping("${openapi.swaggerPetstoreOpenAPI30.base-path:/api/v1}")
public class OrdersApiController implements OrdersApi {

    private final OrdersApiDelegate delegate;

    public OrdersApiController(@org.springframework.beans.factory.annotation.Autowired(required = false) OrdersApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new OrdersApiDelegate() {});
    }

    @Override
    public OrdersApiDelegate getDelegate() {
        return delegate;
    }

}
