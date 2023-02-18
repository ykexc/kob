package com.kob.matchingsystem;

import com.kob.matchingsystem.service.impl.MatchingServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author mqz
 */
@SpringBootApplication
public class  MatchingSystemApplication {
    public static void main(String[] args) {
        MatchingServiceImpl.MATCHING_POOL.start();
        SpringApplication.run(MatchingSystemApplication.class, args);
    }
}
