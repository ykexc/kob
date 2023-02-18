package com.kob.matchingsystem.service;

/**
 * @author mqz
 */
public interface MatchingService {

    String addPlayer(Integer userId, Integer rating, Integer botId);

    String removePlayer(Integer userId);

}
