package com.example.demo.src.likes;

import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LikeProvider {

    private final LikeDao likeDao;
    private final JwtService jwtService;
}
