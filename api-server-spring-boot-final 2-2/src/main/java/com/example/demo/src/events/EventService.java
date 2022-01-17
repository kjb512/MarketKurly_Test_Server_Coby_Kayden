package com.example.demo.src.events;

import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EventService {

    private final EventProvider eventProvider;
    private final EventDao eventDao;
    private final JwtService jwtService;
}
