package com.github.lincolnluiz.crawlerinstitutoidentificacaoal;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "app")
public class ApplicationProperties {

    private final Crawler crawler = new Crawler();
    private final Vacancy vacancy = new Vacancy();
    private final Notification notification = new Notification();

    public Notification getNotification() {
        return notification;
    }

    public Crawler getCrawler() {
        return crawler;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    public static class Notification {

        private String from;
        private List<String> to;

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public List<String> getTo() {
            return to;
        }

        public void setTo(List<String> to) {
            this.to = to;
        }
    }

    public static class Crawler {

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class Vacancy {

        private String place;

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }
    }
}
