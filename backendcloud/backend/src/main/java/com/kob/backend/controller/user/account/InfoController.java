package com.kob.backend.controller.user.account;

import com.kob.backend.service.user.account.InfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author mqz
 */
@RestController
public class InfoController {
    @Resource
    InfoService infoService;

    @GetMapping(value = "/api/user/account/info/")
    public Map<String, String> getInfo() {
        Map<String, String> info = infoService.getInfo();
        info.forEach((key, value) -> System.out.println("key = " + key + "value = " + value));
        return info;
    }
}
