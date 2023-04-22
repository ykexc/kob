package com.kob.backend.controller.user.account.qq;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.kob.backend.service.user.account.qq.QQWebService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author mqz
 */
@RestController
@RequiredArgsConstructor
public class QQWebController {

    private final QQWebService qqWebService;


    @GetMapping("/api/user/account/qq/apply_code/")
    public JSONObject applyCode() {
        return qqWebService.applyCode();
    }

    @GetMapping("/api/user/account/qq/receive_code")
    public JSONObject receiveCode(
            @RequestParam Map<String,String> data
    ) {
        return qqWebService.receiveCode(data.get("state"), data.get("code"));
    }

}
