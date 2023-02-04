package kob.service;

/**
 * @author mqz
 */
public interface MatchingService {

    String addPlayer(Integer userId, Integer rating);

    String removePlayer(Integer userId);

}
