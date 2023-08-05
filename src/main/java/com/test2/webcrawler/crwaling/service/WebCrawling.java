//네이버 경제 크롤링
//    private void naverEcoCrawler(){
//
//        String url = "https://news.naver.com/main/main.naver?mode=LSD&mid=shm&sid1=101#&date=%2000:00:00&page=";
//        Document document = null;
//        StringBuffer strToAnalyze = new StringBuffer();
//        try {
//            for (int i = 1; i <= 10; i++) {
//                document = Jsoup.connect(url + i).get();
//                Elements newsHead = document.getElementsByClass("sh_text_headline nclicks(cls_eco.clsart)");
//                for (org.jsoup.nodes.Element element : newsHead) {
//
//                    strToAnalyze.append(element.text().replaceAll("\\(.*?\\)", "")
//                            .replaceAll("\\[.*?\\]", "").replaceAll("[^a-zA-Z0-9가-힣\\s]", ""));
//
//
//                }
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//


//
//
//    // 네이버 사회 크롤링
//    private void naverSocCrawler(){
//          String url = "https://news.naver.com/main/main.naver?mode=LSD&mid=shm&sid1=102#&date=%2000:00:00&page=";
//        Document document = null;
//        StringBuffer strToAnalyze = new StringBuffer();
//        try {
//            for (int i = 1; i <= 10; i++) {
//                document = Jsoup.connect(url + i).get();
//                Elements newsHead = document.getElementsByClass("sh_text_headline nclicks(cls_nav.clsart)");
//                for (org.jsoup.nodes.Element element : newsHead) {
//
//                    strToAnalyze.append(element.text().replaceAll("\\(.*?\\)", "")
//                            .replaceAll("\\[.*?\\]", "").replaceAll("[^a-zA-Z0-9가-힣\\s]", ""));
//
//
//                }
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    private void naverITCrawler(){
//          String url = "https://news.naver.com/main/main.naver?mode=LSD&mid=shm&sid1=105#&date=%2000:00:00&page=";
//        Document document = null;
//        StringBuffer strToAnalyze = new StringBuffer();
//        try {
//            for (int i = 1; i <= 10; i++) {
//                document = Jsoup.connect(url + i).get();
//                Elements newsHead = document.getElementsByClass("sh_text_headline nclicks(cls_sci.clsart)");
//                for (org.jsoup.nodes.Element element : newsHead) {
//
//                    strToAnalyze.append(element.text().replaceAll(
//                            "\\(.*?\\)", "")
//                            .replaceAll("\\[.*?\\]", "")
//                            .replaceAll("[^a-zA-Z0-9가-힣\\s]", ""));
//
//                }
//
//            }
//            System.out.println(strToAnalyze.toString());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
package com.test2.webcrawler.crwaling.service;

import com.test2.webcrawler.crwaling.dao.*;
import com.test2.webcrawler.crwaling.model.*;
import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class WebCrawling {
    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiURL;
    private final RestTemplate template;
    // Cloud
    private final SocietyWordCloud societyWordCloud;

    private final PoliticsWordCloud politicsWordCloud;
    private final EconomyWordCloud economyWordCloud;
    private final ITWordCloud itWordCloud;
    //


    private final InsertNews insertNews;
    private final Morpheme morpheme;
    private final GPTRequestAndResponse gptRequestAndResponse;
    private String siteLog;
    private String type;


    public WebCrawling(RestTemplate template
            , SocietyWordCloud societyWordCloud
            , PoliticsWordCloud politicsWordCloud
            , EconomyWordCloud economyWordCloud
            , ITWordCloud itWordCloud
            , InsertNews insertNews
            , Morpheme morpheme
            , GPTRequestAndResponse gptRequestAndResponse) {
        this.template = template;
        this.societyWordCloud = societyWordCloud;
        this.politicsWordCloud = politicsWordCloud;
        this.economyWordCloud = economyWordCloud;
        this.itWordCloud = itWordCloud;
        this.insertNews = insertNews;
        this.morpheme = morpheme;
        this.gptRequestAndResponse = gptRequestAndResponse;
    }

    private String naverSearchType;


    /*
        크롤링 스타트
        @Param : 기사 종류
        pol : 정치
        eco : 경제
        nav : 사회
        sci : 경제/과학
        */
    public void crawlerStater(String type) {

        this.type = type;
        String url = "https://news.naver.com/main/main.naver?mode=LSD&mid=shm&sid1=100#&date=%2000:00:00&page=";
        Document document = null;

        StringBuffer naver = new StringBuffer();
        log.info("@!#@! ===> naver crawler start");
        try {
            for (int i = 1; i <= 10; i++) {
                document = Jsoup.connect(url + i).get();
                Elements newsHead = document.getElementsByClass("sh_text_headline nclicks(cls_" + type + ".clsart)");
                for (org.jsoup.nodes.Element element : newsHead) {

                    naver.append(element.text().replaceAll("\\(.*?\\)", "").replaceAll("\\[.*?\\]", "").replaceAll("[^a-zA-Z0-9가-힣\\s]", ""));

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("@!#@! ===> naver crawler end ");
        String daumUrl = "";
        switch (type) {
            case "pol":
                daumUrl = "politics";
                break;

            case "eco":
                daumUrl = "economic";
                break;

            case "nav":
                daumUrl = "society";
                break;

            case "sci":
                daumUrl = "digital";
                break;

        }

        log.info("@!#@! ===> daum crawler start");
        url = "https://news.daum.net/breakingnews/" + daumUrl + "?page=";


        StringBuffer daum = new StringBuffer();
        try {

            for (int i = 1; i <= 10; i++) {

                document = Jsoup.connect(url + i).get();
                Elements listRealtime = document.getElementsByClass("list_news2 list_allnews");
                for (Element element : listRealtime) {
                    Elements aTxt = element.select("a.link_txt");
                    for (Element element1 : aTxt) {
                        daum.append(element1.text()
                                .replaceAll("\\(.*?\\)", "")
                                .replaceAll("\\[.*?\\]", "")
                                .replaceAll("[^a-zA-Z가-힣\\s]", ""));
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("@!#@! ===> daum crawler start");
        Map<String, StringBuffer> portalSiteResult = new HashMap<>();

        portalSiteResult.put("naver", naver);


        portalSiteResult.put("daum", daum);

        morphologicalStarter(portalSiteResult);

        log.info("#@!$@!$ == >call morphologicalStarter");
    }

    private void morphologicalStarter(Map<String, StringBuffer> portalSiteResult) {
        log.info("#@!$@!$ == > morphologicalStarter start");

        StringBuffer naver = portalSiteResult.get("naver");

        morphological("네이버", naver);

        siteLog = "네이버";

        log.info("!@#$@! === > call  naver morphological");

        StringBuffer daum = portalSiteResult.get("daum");

        morphological("다음", daum);
        log.info("!@#$@! === >  call daum morphological");

        log.info("#@!$@!$ == > morphologicalStarter done");

    }


    private void morphological(String site, StringBuffer strToAnalyze) {
        log.info("#!$@!!# === >" + siteLog + "morphological start");

        Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);
        MorphemeVO morphemeVO;

        KomoranResult analyzeResultList = komoran.analyze(strToAnalyze.toString());

        List<String> list = new ArrayList<>();
        List<Token> tokenList = analyzeResultList.getTokenList();

        StringBuffer stringBuffer = new StringBuffer();

        StringBuffer insertBuffer = new StringBuffer();


        for (Token token : tokenList) {
            morphemeVO = new MorphemeVO(token.getMorph(), token.getPos());
            morpheme.insert(morphemeVO);
            insertBuffer.append(token.getMorph());

            if (token.getMorph().equals("NNG")
                    || token.getMorph().equals("NNP")
                    || token.getMorph().equals("VV")
                    || token.getMorph().equals("VA")) {
                log.info("morph === >{} , post {} === >", token.getMorph(), token.getPos());
                stringBuffer.append(token.getMorph() + " ");
            }


        }

        NewsTitleVO newsTitleVO = new NewsTitleVO(site, insertNews.toString(), LocalDateTime.now());
        switch (type) {

            case "pol":
                insertNews.insertPoliticsTitle(newsTitleVO);
                break;
            case "eco":
                insertNews.insertEconomicTitle(newsTitleVO);
                break;

            case "nav":
                insertNews.insertItTitle(newsTitleVO);

                break;

            case "sci":
                insertNews.insertSocietyTitle(newsTitleVO);
                break;

        }
        log.info("#!@$@!# === > call wordCount");
        wordCount(stringBuffer);

        log.info("#!$@!!# === > morphological done");
    }


    private void wordCount(StringBuffer strToAnalyze) {
        log.info("@#!$@!# === > wordCount Start ");
        String[] words = strToAnalyze.toString().split(" ");

        // Map을 사용하여 단어의 빈도수를 세기
        Map<String, Integer> wordCounts = new HashMap<>();
        for (String word : words) {
            Integer count = wordCounts.get(word);
            if (count == null) {
                wordCounts.put(word, 1);
            } else {
                wordCounts.put(word, count + 1);
            }
        }

        // Map.Entry 리스트로 변환
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(wordCounts.entrySet());

        // 빈도수를 기준으로 내림차순 정렬
        entryList.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
        List<String> list = new ArrayList<>();
        // 빈도수 상위 10개의 단어 출력
        for (int i = 0; i < 10 && i < entryList.size(); i++) {
            Map.Entry<String, Integer> entry = entryList.get(i);
            log.info(entry.getKey() + ": " + entry.getValue());
            list.add(entry.getKey());


        }
        log.info("#@!#$ === > call saveCloud ");
        saveCloud(list);
        log.info("@#!$@!# === > wordCount done ");
    }

    private void saveCloud(List<String> list) {
        log.info("@#!$@!# === > saveCloud start");
        CloudVO cloudVO = new CloudVO(
                list.get(0)
                , list.get(1)
                , list.get(2)
                , list.get(3)
                , list.get(4)
                , list.get(5)
                , list.get(6)
                , list.get(7)
                , list.get(8)
                , list.get(9)
                , LocalDateTime.now()
        );
        String prompt = String.join(", ", list) + "이 단어들을 읽고 단어들이 " +
                "긍정적 or 대체로 긍정적 or 중간 or 대체로 부정적 or 부정적 " +
                "으로 이루어져 있는지만" + "하나만 짚어서 말해줘";

        ChatGptRequest request = new ChatGptRequest(model, prompt);
        ChatGptResponse chatGptResponse = template.postForObject(apiURL, request, ChatGptResponse.class);
        String response = chatGptResponse.getChoices().get(0).getMessage().getContent();

        Integer cloudId = null;
        switch (type) {

            case "pol":
                politicsWordCloud.insert(cloudVO);
                cloudId = politicsWordCloud.getMaxId();

                break;

            case "eco":
                economyWordCloud.insert(cloudVO);
                cloudId = economyWordCloud.getMaxId();
                break;

            case "nav":
                itWordCloud.insert(cloudVO);
                cloudId = itWordCloud.getMaxId();
                break;

            case "sci":
                societyWordCloud.insert(cloudVO);
                cloudId = societyWordCloud.getMaxId();
                break;


        }
        RequestAndResponseVO requestAndResponseVO = new RequestAndResponseVO(type, prompt, response, LocalDateTime.now(), cloudId);
        gptRequestAndResponse.insert(requestAndResponseVO);

        log.info("@#!$@!# === > saveCloud and");
    }

}
