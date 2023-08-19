package com.abelkelly.customer.Controllers;

import com.abelkelly.customer.CustomerRecords.CustomerRegistrationRequest;
import com.abelkelly.customer.Services.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistrationRequest request) throws JSONException {
       log.info("new customer registration {}", request);
       customerService.registerCustomer(request);
    }

}
