package com.test2.webcrawler.crwaling.dao;

import com.test2.webcrawler.crwaling.model.NewsTitleVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper

public interface InsertNews{
    @Insert("INSERT INTO it(site, newsTitle, searchTime) VALUES(i_id+1,#{site}, #{newsTitle}, #{searchTime})")
    void insertItTitle(NewsTitleVO newsTitleDTO);
    @Insert("INSERT INTO politics(site, newsTitle, searchTime) VALUES(p_id+1,#{site}, #{newsTitle}, #{searchTime})")
    void insertPoliticsTitle(NewsTitleVO newsTitleDTO);

    @Insert("INSERT INTO society(site, newsTitle, searchTime) VALUES(s_id+1,#{site}, #{newsTitle}, #{searchTime})")
    void insertSocietyTitle(NewsTitleVO newsTitleDTO);

    @Insert("INSERT INTO economic(site, newsTitle, searchTime) VALUES(e_id+1,#{site}, #{newsTitle}, #{searchTime})")
    void insertEconomicTitle(NewsTitleVO newsTitleDTO);
}

