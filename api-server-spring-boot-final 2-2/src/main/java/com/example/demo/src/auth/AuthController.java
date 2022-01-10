package com.example.demo.src.auth;

import com.example.demo.src.users.UserProvider;
import com.example.demo.src.users.UserService;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserProvider userProvider;
    private final UserService userService;
    private final JwtService jwtService;


}
