package com.test2.webcrawler.crwaling.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WebCrawlingSchedul {
    private final WebCrawling webCrawling;

    @Scheduled(cron = "0 0 9 * * ?")
    public void ecoAM() {

        webCrawling.crawlerStater("eco");

    }

    @Scheduled(cron = "0 0 21 * * ?")
    public void ecoPM() {
        webCrawling.crawlerStater("eco");

    }

    @Scheduled(cron = "0 5 9 * * ?")
    public void polAM() {
        webCrawling.crawlerStater("pol");


    }

    @Scheduled(cron = "0 5 21 * * ?")
    public void polPM() {
        webCrawling.crawlerStater("pol");
    }

    @Scheduled(cron = "0 10 9 * * ?")
    public void navAM() {
        webCrawling.crawlerStater("nav");

    }

    @Scheduled(cron = "0 10 21 * * ?")
    public void navPM() {
        webCrawling.crawlerStater("nav");
    }

    @Scheduled(cron = "0 15 9 * * ?")
    public void sciAM() {
        webCrawling.crawlerStater("sci");


    }

    @Scheduled(cron = "0 15 21 * * ?")
    public void sciPM() {
        webCrawling.crawlerStater("sci");

    }
}
