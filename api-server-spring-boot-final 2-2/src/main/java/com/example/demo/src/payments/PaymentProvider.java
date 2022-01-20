package com.example.demo.src.payments;

import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentProvider {

    private final PaymentDao paymentDao;
    private final JwtService jwtService;
}
