package com.kob.botrunningsystem.service.impl.utils;

import com.kob.botrunningsystem.utils.BotInterface;
import org.joor.Reflect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

/**
 * @author mqz
 */
@Component
public class Consumer extends Thread {

    private Bot bot;
    private static RestTemplate restTemplate;
    public static final String RECEIVE_BOT_MOVE_URL = "http://127.0.0.1:3000/pk/receive/bot/move/";
    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        Consumer.restTemplate = restTemplate;
    }

    public void startTimeOut(long timeOut, Bot bot) {
        this.bot = bot;
        this.start();
        try {
            this.join(timeOut);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.interrupt();
        }
    }


    @Override
    public void run() {

        UUID uuid = UUID.randomUUID();
        String randomId = uuid.toString().substring(0, 8);
        BotInterface botInterface = Reflect.compile(
                "com.kob.botrunningsystem.utils.Bot" + randomId,
                addUuid(bot.getBotCode(), randomId)
        ).create().get();
        //System.out.println(bot.getUserId() + " direction: " +botInterface.nextMove(bot.getInput()));
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("user_id", bot.getUserId().toString());
        data.add("direction", botInterface.nextMove(bot.getInput()).toString());
        restTemplate.postForObject(RECEIVE_BOT_MOVE_URL, data, String.class);
    }

    private String addUuid(String content, String uid) {
        int k = content.indexOf(" implements com.kob.botrunningsystem.utils.BotInterface");
        return content.substring(0, k) + uid + content.substring(k);
    }
}
