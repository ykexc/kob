package com.kob.botrunningsystem.service.impl.utils;

import java.util.ArrayDeque;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author mqz
 */
public class BotPool extends Thread{

    private static final ReentrantLock LOCK = new ReentrantLock();
    private static final Condition CONDITION = LOCK.newCondition();

    private static final ArrayDeque<Bot> bots = new ArrayDeque<>();

    public void addBot(Integer userId, String botCode, String input) {
        LOCK.lock();
        try {
            bots.addLast(new Bot(userId, botCode, input));
            CONDITION.signalAll();
        } finally {
            LOCK.unlock();
        }
    }


    @Override
    public void run() {
        while (true) {
            LOCK.lock();
            if (bots.isEmpty()) {
                try {
                    CONDITION.await();
                } catch (Exception e) {
                    e.printStackTrace();
                    LOCK.unlock();
                    break;
                }
            } else {
                Bot bot = bots.pollFirst();
                LOCK.unlock();
                consume(bot);
            }
        }
    }
    private void consume(Bot bot) {
        Consumer consumer = new Consumer();
        consumer.startTimeOut(2000, bot);
    }

}
