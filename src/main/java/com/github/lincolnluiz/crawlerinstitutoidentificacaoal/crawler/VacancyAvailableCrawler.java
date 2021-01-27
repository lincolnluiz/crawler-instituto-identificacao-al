package com.github.lincolnluiz.crawlerinstitutoidentificacaoal.crawler;

import com.github.lincolnluiz.crawlerinstitutoidentificacaoal.ApplicationProperties;
import com.github.lincolnluiz.crawlerinstitutoidentificacaoal.domain.Vacancy;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VacancyAvailableCrawler {

    public final static Logger log = LoggerFactory.getLogger(VacancyAvailableCrawler.class);

    public static final int PLACE_INDEX = 0;
    public static final int PERIOD_INDEX = 1;
    public static final int AVAILABLE_INDEX = 2;

    private final ApplicationProperties applicationProperties;
    private final WebPageProcessor webPageProcessor;

    public VacancyAvailableCrawler(ApplicationProperties applicationProperties,
                                   WebPageProcessor webPageProcessor) {
        this.applicationProperties = applicationProperties;
        this.webPageProcessor = webPageProcessor;
    }

    public List<Vacancy> crawler() {

        String url = applicationProperties.getCrawler().getUrl();
        log.info("Sarting crawler: {}", url);
        Document document = webPageProcessor.getDocumentFromUrl(url);
        log.info("Finish crawler: {}", url);

        Elements rows = getRowsEquivalentToVacancies(document);

        ArrayList<Vacancy> vacancies = new ArrayList<>();

        for (Element row : rows) {
            Elements columns = row.select("td");

            Vacancy vacancy = new Vacancy(
                    getTextFromColumn(columns, PLACE_INDEX),
                    getTextFromColumn(columns, PERIOD_INDEX)
            );

            vacancies.add(vacancy);
        }

        log.info("{} vacancies found in total", vacancies.size());

        return vacancies;
    }

    private String getTextFromColumn(Elements columns, int columnIndex) {
        return columns.get(columnIndex).text();
    }

    private Elements getRowsEquivalentToVacancies(Document document) {
        Element table = document.select("table").get(0);
        Element tbody = table.select("tbody").get(0);
        return tbody.select("tr");
    }

}
