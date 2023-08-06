package com.test2.webcrawler.crwaling.dao;

import com.test2.webcrawler.crwaling.model.CloudVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PoliticsWordCloud  extends DBInterface<CloudVO> {

    @Override
    @Insert("INSERT INTO p_cloud (create_at, content1, content2, content3, content4, content5, content6, content7, content8, content9, content10) " +
            "VALUES (#{createdAt}, #{content1}, #{content2}, #{content3}, #{content4}, #{content5}, #{content6}, #{content7}, #{content8}, #{content9}, #{content10})")
    void insert(CloudVO cloudVO);

    @Override
    @Select("select max(id) from p_cloud ")
    Integer getMaxId();
}
