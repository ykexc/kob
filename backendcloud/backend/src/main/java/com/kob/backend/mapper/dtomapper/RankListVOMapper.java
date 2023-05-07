package com.kob.backend.mapper.dtomapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kob.backend.pojo.dto.RankListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author mqz
 */
@Mapper
public interface RankListVOMapper extends BaseMapper<RankListVO> {



    @Select("select u.username, u.photo, u.rating, ifnull(max(b.rating), 0) bot_rating from user u left join bot b on u.id = b.user_id group by u.username, u.rating order by rating desc")
    IPage<RankListVO> selectRankListPage(Page<RankListVO> page);
}
