package com.kob.botrunningsystem.service.impl;

import com.kob.botrunningsystem.service.BotRunningService;
import com.kob.botrunningsystem.service.impl.utils.BotPool;
import org.springframework.stereotype.Service;

/**
 * @author mqz
 */
@Service
public class BotRunningServiceImpl implements BotRunningService {

    public static BotPool botPool = new BotPool();
    @Override
    public String addBot(Integer userId, String botCode, String input) {
//        System.out.println(userId + " " + botCode + " " + input);
        botPool.addBot(userId, botCode, input);
        return "addBot success";
    }
}
