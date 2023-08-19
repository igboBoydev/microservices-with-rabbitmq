package com.abelkelly.customer.Services;

import com.abelkelly.customer.CustomerRecords.CustomerRegistrationRequest;
import com.abelkelly.customer.FraudRecord.FraudCheckResponse;
import com.abelkelly.customer.Models.Customer;
import com.abelkelly.customer.Repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    public void registerCustomer(CustomerRegistrationRequest request) throws JSONException {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        // todo: check if user email is valid
        // todo: check is user email is not already used
        // todo: check if customer is fraudster
        // todo: customer in db
        customerRepository.saveAndFlush(customer);
        FraudCheckResponse response = restTemplate.getForObject("http://FRAUD/api/v1/fraud-check/{customerId}", FraudCheckResponse.class, customer.getId());
        if(response.isFraudster()){
            throw new IllegalStateException("Fraudster");
        }

        System.out.println("fraudster = " + response.isFraudster());


        // todo: send notification
        if(!response.isFraudster()) {
            JSONObject requests = new JSONObject();
            byte name = 0;
            byte password =0;
            requests.put("username", name);
            requests.put("password", password);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<String>(requests.toString(), headers);
            ResponseEntity<String> notificationResponse = restTemplate.exchange("http://NOTIFICATION/api/v1/notification/publish", HttpMethod.POST, entity, String.class);
            System.out.println("notification status = " + notificationResponse);
        }
    }
}
