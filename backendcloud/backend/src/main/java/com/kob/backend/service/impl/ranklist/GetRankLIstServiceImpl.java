package com.kob.backend.service.impl.ranklist;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.User;
import com.kob.backend.service.ranklist.GetRankListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author mqz
 */
@Service
public class GetRankLIstServiceImpl implements GetRankListService {

    @Resource
    private UserMapper userMapper;

    @Override
    public JSONObject getRankList(Integer pageId) {
        IPage<User> iPage = new Page<>(pageId, 3);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("rating");
        List<User> users = userMapper.selectPage(iPage, wrapper).getRecords();
        users.forEach(e -> e.setPassword(""));
        JSONObject resp = new JSONObject();
        resp.put("users", users);
        resp.put("users_count", userMapper.selectCount(null));
        return resp;
    }
}
