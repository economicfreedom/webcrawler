package com.test2.webcrawler.crwaling.dao;

import com.test2.webcrawler.crwaling.model.RequestAndResponseVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GPTRequestAndResponse extends DBInterface<RequestAndResponseVO> {

    @Insert("INSERT INTO request_and_response (cloud_id,news_type, request, response, create_at) VALUES (#{cloudId},#{newsType}, #{request}, #{response}, #{createdAt})")
    void insert(RequestAndResponseVO requestAndResponseVO);


}
