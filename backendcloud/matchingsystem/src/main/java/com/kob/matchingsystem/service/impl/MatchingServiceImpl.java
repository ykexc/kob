package com.kob.matchingsystem.service.impl;

import com.kob.matchingsystem.service.MatchingService;
import com.kob.matchingsystem.service.impl.util.MatchingPool;
import org.springframework.stereotype.Service;

/**
 * @author mqz
 */
@Service
public class MatchingServiceImpl implements MatchingService {

    public static final MatchingPool MATCHING_POOL = new MatchingPool();

    @Override
    public String addPlayer(Integer userId, Integer rating, Integer botId) {
        System.out.println("add user userId :" + userId + " rating:" + rating + "botId:" + botId);
        MATCHING_POOL.addPlayer(userId, rating, botId);
        return "add success";
    }

    @Override
    public String removePlayer(Integer userId) {
        System.out.println("remove id :" + userId);
        MATCHING_POOL.removePlayer(userId);
        return "remove success";
    }
}
