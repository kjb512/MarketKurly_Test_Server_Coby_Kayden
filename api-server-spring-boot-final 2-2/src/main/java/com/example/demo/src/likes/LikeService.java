package com.example.demo.src.likes;


import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LikeService {

    private final LikeDao likeDao;
    private final LikeProvider likeProvider;
    private final JwtService jwtService;
}
