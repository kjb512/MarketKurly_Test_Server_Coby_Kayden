package com.example.demo.src.events;

import com.example.demo.config.BaseException;
import com.example.demo.src.events.model.GetEventRes;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@RequiredArgsConstructor
@Service
public class EventProvider {
    private final EventDao eventsDao;
    private final JwtService jwtService;

    public GetEventRes getEvent(int eventIdx) throws BaseException {
        try {
            GetEventRes getEventRes = eventsDao.getEvent(eventIdx);
            return getEventRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }

    }
}
