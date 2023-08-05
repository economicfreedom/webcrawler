package com.test2.webcrawler;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;

public class KomoranTest {
    public static void main(String[] args) {
        String url = "https://news.naver.com/main/main.naver?mode=LSD&mid=shm&sid1=100#&date=%2000:00:00&page=";



        Document document = null;
        StringBuilder strToAnalyze = new StringBuilder();
        try {
            document = Jsoup.connect(url+1).get();
            Elements newsHead = document.getElementsByClass("sh_text_headline nclicks(cls_pol.clsart)");

            for (int i = 0; i < newsHead.size(); i++) {

                strToAnalyze.append(newsHead.get(i).text());

            }







        }catch (Exception e){
            e .printStackTrace();
        }

        Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);


        KomoranResult analyzeResultList = komoran.analyze(strToAnalyze.toString());

        System.out.println(analyzeResultList.getPlainText());

        List<Token> tokenList = analyzeResultList.getTokenList();
        for (Token token : tokenList) {
            System.out.format("%s/%s\n" ,token.getMorph(), token.getPos());
        }


    }




}
