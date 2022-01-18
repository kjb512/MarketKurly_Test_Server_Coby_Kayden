package com.example.demo.src.events;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.events.model.GetEventRes;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventProvider eventsProvider;
    private final EventService eventsService;
    private final JwtService jwtService;

    @GetMapping("/{eventIdx}")
    public BaseResponse<GetEventRes> getEvent(@PathVariable int eventIdx) {
        try{

            GetEventRes getEventRes = eventsProvider.getEvent(eventIdx);
            return new BaseResponse<>(getEventRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
