package com.test2.webcrawler.crwaling.dao;

import com.test2.webcrawler.crwaling.model.CloudVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PoliticsWordCloud  extends InsertInterface<CloudVO> {

    @Override
    @Insert("INSERT INTO p_cloud (created_at, content1, content2, content3, content4, content5, content6, content7, content8, content9, content10) " +
            "VALUES (#{createdAt}, #{content1}, #{content2}, #{content3}, #{content4}, #{content5}, #{content6}, #{content7}, #{content8}, #{content9}, #{content10})")
    void insert(CloudVO cloudVO);
}
