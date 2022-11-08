package com.kob.backend.service.user.account;

import com.kob.backend.pojo.User;
import com.kob.backend.service.IService;

import java.util.Map;

/**
 * @author mqz
 */
public interface RegisterService extends IService<User> {
    Map<String, String> register (String username, String password, String confirmPassword);
}
