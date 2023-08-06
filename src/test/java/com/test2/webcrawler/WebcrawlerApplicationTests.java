package com.test2.webcrawler;

import com.test2.webcrawler.crwaling.service.WebCrawling;
import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class WebcrawlerApplicationTests {

    @Autowired
    private WebCrawling webCrawling;
    @Test
    void contextLoads() {
        System.out.println("asd");
    }

    @Test
    void 네이버정치크롤링() throws IOException {
        String url = "https://news.naver.com/main/main.naver?mode=LSD&mid=shm&sid1=100#&date=%2000:00:00&page=";
        Document document = null;

        StringBuffer strToAnalyze = new StringBuffer();
        try {
            for (int i = 1; i <= 10; i++) {
                document = Jsoup.connect(url + i).get();
                Elements newsHead = document.getElementsByClass("sh_text_headline nclicks(cls_pol.clsart)");
                for (org.jsoup.nodes.Element element : newsHead) {

                    strToAnalyze.append(element.text()
                            .replaceAll("\\(.*?\\)", "")
                            .replaceAll("\\[.*?\\]", "")
                            .replaceAll("[^a-zA-Z0-9가-힣\\s]", "")
                    );

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

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

        // 빈도수 상위 10개의 단어 출력
        for (int i = 0; i < 10 && i < entryList.size(); i++) {
            Map.Entry<String, Integer> entry = entryList.get(i);
            System.out.println(entry.getKey() + ": " + entry.getValue());
            //TODO 여기서 클라우드 컬럼에 데이터 추가
            //TODO String에 key값을 저장하고 지피티한테 물어보기

        }


        Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);


        KomoranResult analyzeResultList = komoran.analyze(strToAnalyze.toString());

        System.out.println(analyzeResultList.getPlainText());

        List<Token> tokenList = analyzeResultList.getTokenList();
        for (Token token : tokenList) {
            System.out.format("%s/%s\n", token.getMorph(), token.getPos());
            //TODO 여기에 데이터베이스에 형태소 컬럼에 값 추가

        }
    }

    @Test
    void 네이버경제크롤링() {
        String url = "https://news.naver.com/main/main.naver?mode=LSD&mid=shm&sid1=101#&date=%2000:00:00&page=";
        Document document = null;
        StringBuffer strToAnalyze = new StringBuffer();
        try {
            for (int i = 1; i <= 10; i++) {
                document = Jsoup.connect(url + i).get();
                Elements newsHead = document.getElementsByClass("sh_text_headline nclicks(cls_eco.clsart)");
                for (org.jsoup.nodes.Element element : newsHead) {

                    strToAnalyze.append(element.text().replaceAll("\\(.*?\\)", "")
                            .replaceAll("\\[.*?\\]", "").replaceAll("[^a-zA-Z0-9가-힣\\s]", ""));


                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

        // 빈도수 상위 10개의 단어 출력
        for (int i = 0; i < 10 && i < entryList.size(); i++) {
            Map.Entry<String, Integer> entry = entryList.get(i);
            System.out.println(entry.getKey() + ": " + entry.getValue());
            //TODO 여기서 클라우드 컬럼에 데이터 추가
            //TODO String에 key값을 저장하고 지피티한테 물어보기

        }


        Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);


        KomoranResult analyzeResultList = komoran.analyze(strToAnalyze.toString());

        System.out.println(analyzeResultList.getPlainText());

        List<Token> tokenList = analyzeResultList.getTokenList();
        for (Token token : tokenList) {
            System.out.format("%s/%s\n", token.getMorph(), token.getPos());
            //TODO 여기에 데이터베이스에 형태소 컬럼에 값 추가


        }
    }

    @Test
    void 네이버사회() {
        String url = "https://news.naver.com/main/main.naver?mode=LSD&mid=shm&sid1=102#&date=%2000:00:00&page=";
        Document document = null;
        StringBuffer strToAnalyze = new StringBuffer();
        try {
            for (int i = 1; i <= 10; i++) {
                document = Jsoup.connect(url + i).get();
                Elements newsHead = document.getElementsByClass("sh_text_headline nclicks(cls_nav.clsart)");
                for (org.jsoup.nodes.Element element : newsHead) {

                    strToAnalyze.append(element.text().replaceAll("\\(.*?\\)", "")
                            .replaceAll("\\[.*?\\]", "").replaceAll("[^a-zA-Z0-9가-힣\\s]", ""));


                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

        // 빈도수 상위 10개의 단어 출력
        for (int i = 0; i < 10 && i < entryList.size(); i++) {
            Map.Entry<String, Integer> entry = entryList.get(i);
            System.out.println(entry.getKey() + ": " + entry.getValue());
            //TODO 여기서 클라우드 컬럼에 데이터 추가
            //TODO String에 key값을 저장하고 지피티한테 물어보기

        }


        Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);


        KomoranResult analyzeResultList = komoran.analyze(strToAnalyze.toString());

        System.out.println(analyzeResultList.getPlainText());

        List<Token> tokenList = analyzeResultList.getTokenList();
        for (Token token : tokenList) {
            System.out.format("%s/%s\n", token.getMorph(), token.getPos());
            //TODO 여기에 데이터베이스에 형태소 컬럼에 값 추가


        }
    }

    @Test
    void 네이버크롤링IT과학() {
        String url = "https://news.naver.com/main/main.naver?mode=LSD&mid=shm&sid1=105#&date=%2000:00:00&page=";
        Document document = null;
        StringBuffer strToAnalyze = new StringBuffer();
        try {
            for (int i = 1; i <= 10; i++) {
                document = Jsoup.connect(url + i).get();
                Elements newsHead = document.getElementsByClass("sh_text_headline nclicks(cls_sci.clsart)");
                for (org.jsoup.nodes.Element element : newsHead) {

                    strToAnalyze.append(element.text().replaceAll(
                                    "\\(.*?\\)", "")
                            .replaceAll("\\[.*?\\]", "")
                            .replaceAll("[^a-zA-Z0-9가-힣\\s]", ""));

                }

            }
            System.out.println(strToAnalyze.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
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

        // 빈도수 상위 10개의 단어 출력
        for (int i = 0; i < 10 && i < entryList.size(); i++) {
            Map.Entry<String, Integer> entry = entryList.get(i);
            System.out.println(entry.getKey() + ": " + entry.getValue());
            //TODO 여기서 클라우드 컬럼에 데이터 추가
            //TODO String에 key값을 저장하고 지피티한테 물어보기

        }


        Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);


        KomoranResult analyzeResultList = komoran.analyze(strToAnalyze.toString());

        System.out.println(analyzeResultList.getPlainText());

        List<Token> tokenList = analyzeResultList.getTokenList();
        for (Token token : tokenList) {
            System.out.format("%s/%s\n", token.getMorph(), token.getPos());
            //TODO 여기에 데이터베이스에 형태소 컬럼에 값 추가


        }

    }

    @Test
    void 다음사회뉴스() {
        String url = "https://news.daum.net/breakingnews/society?page=";
        Document document = null;
        StringBuffer strToAnalyze = new StringBuffer();
        int k = 0;
        try {

            StringBuffer a = new StringBuffer();
            for (int i = 1; i <= 10; i++) {
                System.out.println();
                System.out.println("@#!#$ === > url : " + url + i);
                document = Jsoup.connect(url + i).get();
                Elements listRealtime = document.getElementsByClass("list_news2 list_allnews");
                for (Element element : listRealtime) {
                    Elements a1 = element.select("a.link_txt");

                    for (Element element1 : a1) {
                        System.out.println(element1.text()
                                .replaceAll("\\(.*?\\)", "")
                                .replaceAll("\\[.*?\\]", "")
                                .replaceAll("[^a-zA-Z가-힣\\s]", ""));

                        strToAnalyze.append(element1.text());
                    }
                }
            }

            System.out.println(strToAnalyze.toString());


        } catch (Exception e) {
            e.printStackTrace();
        }


        Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);


        KomoranResult analyzeResultList = komoran.analyze(strToAnalyze.toString());

        System.out.println(analyzeResultList.getPlainText());

        List<Token> tokenList = analyzeResultList.getTokenList();
        for (Token token : tokenList) {
            System.out.format("%s/%s\n", token.getMorph(), token.getPos());
            //TODO 여기에 데이터베이스에 형태소 컬럼에 값 추가


        }
        System.out.println("k의 값" + k);


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

        // 빈도수 상위 10개의 단어 출력
        for (int i = 0; i < 10 && i < entryList.size(); i++) {
            Map.Entry<String, Integer> entry = entryList.get(i);
            System.out.println(entry.getKey() + ": " + entry.getValue());
            //TODO 여기서 클라우드 컬럼에 데이터 추가
            //TODO String에 key값을 저장하고 지피티한테 물어보기

        }

    }


    @Test
    void 다음정치뉴스() {
        String url = "https://news.daum.net/breakingnews/politics?page=";
        Document document = null;
        StringBuffer strToAnalyze = new StringBuffer();
        int k = 0;
        try {

            StringBuffer a = new StringBuffer();
            for (int i = 1; i <= 10; i++) {
                System.out.println();
                System.out.println("@#!#$ === > url : " + url + i);
                document = Jsoup.connect(url + i).get();
                Elements listRealtime = document.getElementsByClass("list_news2 list_allnews");
                for (Element element : listRealtime) {
                    Elements a1 = element.select("a.link_txt");

                    for (Element element1 : a1) {
                        System.out.println(element1.text()
                                .replaceAll("\\(.*?\\)", "")
                                .replaceAll("\\[.*?\\]", "")
                                .replaceAll("[^a-zA-Z가-힣\\s]", ""));

                        strToAnalyze.append(element1.text());
                    }
                }
            }

            System.out.println(strToAnalyze.toString());


        } catch (Exception e) {
            e.printStackTrace();
        }


        Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);


        KomoranResult analyzeResultList = komoran.analyze(strToAnalyze.toString());

        System.out.println(analyzeResultList.getPlainText());

        List<Token> tokenList = analyzeResultList.getTokenList();
        for (Token token : tokenList) {
            System.out.format("%s/%s\n", token.getMorph(), token.getPos());
            //TODO 여기에 데이터베이스에 형태소 컬럼에 값 추가


        }
        System.out.println("k의 값" + k);


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

        // 빈도수 상위 10개의 단어 출력
        for (int i = 0; i < 10 && i < entryList.size(); i++) {
            Map.Entry<String, Integer> entry = entryList.get(i);
            System.out.println(entry.getKey() + ": " + entry.getValue());
            //TODO 여기서 클라우드 컬럼에 데이터 추가
            //TODO String에 key값을 저장하고 지피티한테 물어보기

        }
    }

    @Test
    void 다음경제뉴스() {
        String url = "https://news.daum.net/breakingnews/economic?page=";
        Document document = null;
        StringBuffer strToAnalyze = new StringBuffer();
        int k = 0;
        try {

            StringBuffer a = new StringBuffer();
            for (int i = 1; i <= 10; i++) {
                System.out.println();
                System.out.println("@#!#$ === > url : " + url + i);
                document = Jsoup.connect(url + i).get();
                Elements listRealtime = document.getElementsByClass("list_news2 list_allnews");
                for (Element element : listRealtime) {
                    Elements a1 = element.select("a.link_txt");

                    for (Element element1 : a1) {
                        System.out.println(element1.text()
                                .replaceAll("\\(.*?\\)", "")
                                .replaceAll("\\[.*?\\]", "")
                                .replaceAll("[^a-zA-Z가-힣\\s]", ""));

                        strToAnalyze.append(element1.text());
                    }
                }
            }

            System.out.println(strToAnalyze.toString());


        } catch (Exception e) {
            e.printStackTrace();
        }


        Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);


        KomoranResult analyzeResultList = komoran.analyze(strToAnalyze.toString());

        System.out.println(analyzeResultList.getPlainText());

        List<Token> tokenList = analyzeResultList.getTokenList();
        for (Token token : tokenList) {
            System.out.format("%s/%s\n", token.getMorph(), token.getPos());
            //TODO 여기에 데이터베이스에 형태소 컬럼에 값 추가


        }


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

        // 빈도수 상위 10개의 단어 출력
        for (int i = 0; i < 10 && i < entryList.size(); i++) {
            Map.Entry<String, Integer> entry = entryList.get(i);
            System.out.println(entry.getKey() + ": " + entry.getValue());
            //TODO 여기서 클라우드 컬럼에 데이터 추가
            //TODO String에 key값을 저장하고 지피티한테 물어보기

        }
    }

    @Test
    void 다음IT뉴스() {
        String url = "https://news.daum.net/breakingnews/digital?page=";
        Document document = null;
        StringBuffer strToAnalyze = new StringBuffer();
        int k = 0;
        try {

            StringBuffer a = new StringBuffer();
            for (int i = 1; i <= 10; i++) {
                System.out.println();
                System.out.println("@#!#$ === > url : " + url + i);
                document = Jsoup.connect(url + i).get();
                Elements listRealtime = document.getElementsByClass("list_news2 list_allnews");
                for (Element element : listRealtime) {
                    Elements a1 = element.select("a.link_txt");

                    for (Element element1 : a1) {


                        strToAnalyze.append(element1.text().replaceAll("\\(.*?\\)", "")
                                .replaceAll("\\[.*?\\]", "")
                                .replaceAll("[^a-zA-Z가-힣\\s]", ""));
                    }
                }
            }

            System.out.println(strToAnalyze.toString());


        } catch (Exception e) {
            e.printStackTrace();
        }


        Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);


        KomoranResult analyzeResultList = komoran.analyze(strToAnalyze.toString());

        System.out.println(analyzeResultList.getPlainText());

        StringBuffer stringBuffer = new StringBuffer();
        List<Token> tokenList = analyzeResultList.getTokenList();
        for (Token token : tokenList) {
            System.out.format("%s/%s\n", token.getMorph(), token.getPos());
            //TODO 여기에 데이터베이스에 형태소 컬럼에 값 추가
            stringBuffer.append(token.getMorph());
        }
        System.out.println(stringBuffer.toString());
        System.out.println("k의 값" + k);


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

        // 빈도수 상위 10개의 단어 출력
        for (int i = 0; i < 10 && i < entryList.size(); i++) {
            Map.Entry<String, Integer> entry = entryList.get(i);
            System.out.println(entry.getKey() + ": " + entry.getValue());
            //TODO 여기서 클라우드 컬럼에 데이터 추가
            //TODO String에 key값을 저장하고 지피티한테 물어보기

        }
    }

    @Test
    void 크롤러테스트(){
        webCrawling.crawlerStater("pol");
        webCrawling.crawlerStater("eco");
        webCrawling.crawlerStater("nav");
        webCrawling.crawlerStater("sci");
    }
}
