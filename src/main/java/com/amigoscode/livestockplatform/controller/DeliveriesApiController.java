package com.amigoscode.livestockplatform.controller;

import com.amigoscodelivestock_platform.api.DeliveriesApi;
import com.amigoscodelivestock_platform.api.DeliveriesApiDelegate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-04-09T17:10:08.534310300+02:00[Europe/Paris]")
@Controller
@RequestMapping("${openapi.swaggerPetstoreOpenAPI30.base-path:/api/v1}")
public class DeliveriesApiController implements DeliveriesApi {

    private final DeliveriesApiDelegate delegate;

    public DeliveriesApiController(@org.springframework.beans.factory.annotation.Autowired(required = false) DeliveriesApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new DeliveriesApiDelegate() {});
    }

    @Override
    public DeliveriesApiDelegate getDelegate() {
        return delegate;
    }

}
