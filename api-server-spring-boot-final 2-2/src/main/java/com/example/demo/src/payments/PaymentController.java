package com.example.demo.src.payments;

import com.example.demo.src.payments.model.GetPaymentReq;
import com.example.demo.utils.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final PaymentProvider paymentProvider;
    private final JwtService jwtService;


    @GetMapping("")
    public String payments(@RequestBody GetPaymentReq getPaymentReq) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.tosspayments.com/v1/payments/key-in"))
                .header("Authorization", "Basic dGVzdF9za183RExKT3BtNVFybGJ3TVA3Yk1BM1BOZHhiV25ZOg==")
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString("{\"amount\":\""+getPaymentReq.getAmount()+"\",\"orderId\":\""+getPaymentReq.getOrderId()+"\",\"orderName\":\""+getPaymentReq.getOrderName()+"\",\"cardNumber\":\""+getPaymentReq.getCardNumber()+"\",\"cardExpirationYear\":\""+getPaymentReq.getCardExpirationYear()+"\",\"cardExpirationMonth\":\""+getPaymentReq.getCardExpirationMonth()+"\",\"cardPassword\":\""+getPaymentReq.getCardPassword()+"\",\"customerBirthday\":\""+getPaymentReq.getCustomerBirthday()+"\"}"))
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response.body();
    }

    @GetMapping("cancel")
    public String cancel(@RequestParam String paymentKey) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.tosspayments.com/v1/payments/"+paymentKey+"/cancel"))
                .header("Authorization", "Basic dGVzdF9za183RExKT3BtNVFybGJ3TVA3Yk1BM1BOZHhiV25ZOg==")
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString("{\"cancelReason\":\"변심\"}"))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

}
