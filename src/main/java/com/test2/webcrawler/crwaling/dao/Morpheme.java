package com.test2.webcrawler.crwaling.dao;

import com.test2.webcrawler.crwaling.model.MorphemeVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Morpheme  extends InsertInterface<MorphemeVO> {

    @Insert("INSERT INTO a_morph ( sub, category) VALUES (#{sub}, #{category})")
    @Override
    void insert(MorphemeVO morphemeVO);
}
