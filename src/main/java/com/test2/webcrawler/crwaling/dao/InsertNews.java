package com.test2.webcrawler.crwaling.dao;

import com.test2.webcrawler.crwaling.model.NewsTitleVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper

public interface InsertNews{
    @Insert("INSERT INTO it(news_title, search_time) VALUES( #{newsTitle}, #{searchTime})")
    void insertItTitle(NewsTitleVO newsTitleDTO);
    @Insert("INSERT INTO politics( news_title, search_time) VALUES( #{newsTitle}, #{searchTime})")
    void insertPoliticsTitle(NewsTitleVO newsTitleDTO);

    @Insert("INSERT INTO society( news_title, search_time) VALUES(#{newsTitle}, #{searchTime})")
    void insertSocietyTitle(NewsTitleVO newsTitleDTO);

    @Insert("INSERT INTO economy( news_title, search_time) VALUES( #{newsTitle}, #{searchTime})")
    void insertEconomicTitle(NewsTitleVO newsTitleDTO);

//    @Select("select max(e_id)+1 from economy")
//    Integer ecoMaxId();
//
//    @Select("select max(s_id)+1 from society")
//    Integer socMaxId();
//    @Select("select max(p_id)+1 from politics")
//    Integer polMaxId();
//    @Select("select max(i_id)+1 from it")
//    Integer itMaxId();

}

