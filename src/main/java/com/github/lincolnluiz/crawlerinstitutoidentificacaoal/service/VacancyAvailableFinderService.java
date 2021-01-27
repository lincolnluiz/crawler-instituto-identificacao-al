package com.github.lincolnluiz.crawlerinstitutoidentificacaoal.service;

import com.github.lincolnluiz.crawlerinstitutoidentificacaoal.crawler.VacancyAvailableCrawler;
import com.github.lincolnluiz.crawlerinstitutoidentificacaoal.domain.Vacancy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VacancyAvailableFinderService {

    private final VacancyAvailableCrawler vacancyAvailableCrawler;

    public VacancyAvailableFinderService(VacancyAvailableCrawler vacancyAvailableCrawler) {
        this.vacancyAvailableCrawler = vacancyAvailableCrawler;
    }

    public Optional<Vacancy> findByPlace(String place) {
        List<Vacancy> vacancies = vacancyAvailableCrawler.crawler();
        return vacancies.stream()
                .filter(vacancy -> place.equalsIgnoreCase(vacancy.getPlace()))
                .findFirst();
    }

}
