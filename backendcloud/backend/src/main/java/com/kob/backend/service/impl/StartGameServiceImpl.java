package com.kob.backend.service.impl;

import com.kob.backend.consumer.WebSocketServer;
import com.kob.backend.service.pk.StartGameService;
import org.springframework.stereotype.Service;

/**
 * @author mqz
 */
@Service
public class StartGameServiceImpl implements StartGameService {
    @Override
    public String startGame(Integer aId, Integer bId) {
        System.out.println("startGame" + " " + aId + " " +bId);
        WebSocketServer.startGame(aId, bId);
        return "startGame";
    }
}
