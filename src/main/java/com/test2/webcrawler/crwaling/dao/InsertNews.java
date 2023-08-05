package com.test2.webcrawler.crwaling.dao;

import com.test2.webcrawler.crwaling.model.NewsTitleVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper

public interface InsertNews{
    @Insert("INSERT INTO it(i_id,site, news_title, search_time) VALUES(i_id+1,#{site}, #{newsTitle}, #{searchTime})")
    void insertItTitle(NewsTitleVO newsTitleDTO);
    @Insert("INSERT INTO politics(p_id,site, news_title, search_time) VALUES(p_id+1,#{site}, #{newsTitle}, #{searchTime})")
    void insertPoliticsTitle(NewsTitleVO newsTitleDTO);

    @Insert("INSERT INTO society(s_id,site, news_title, search_time) VALUES(s_id+1,#{site}, #{newsTitle}, #{searchTime})")
    void insertSocietyTitle(NewsTitleVO newsTitleDTO);

    @Insert("INSERT INTO economic(e_id,site, news_title, search_time) VALUES(e_id+1,#{site}, #{newsTitle}, #{searchTime})")
    void insertEconomicTitle(NewsTitleVO newsTitleDTO);
}

