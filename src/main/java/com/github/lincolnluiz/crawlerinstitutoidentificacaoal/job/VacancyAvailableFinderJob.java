package com.github.lincolnluiz.crawlerinstitutoidentificacaoal.job;

import com.github.lincolnluiz.crawlerinstitutoidentificacaoal.ApplicationProperties;
import com.github.lincolnluiz.crawlerinstitutoidentificacaoal.domain.Vacancy;
import com.github.lincolnluiz.crawlerinstitutoidentificacaoal.notificaction.VacancyNotificationService;
import com.github.lincolnluiz.crawlerinstitutoidentificacaoal.service.VacancyAvailableFinderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class VacancyAvailableFinderJob {

    public final static Logger log = LoggerFactory.getLogger(VacancyAvailableFinderJob.class);

    private final VacancyAvailableFinderService vacancyAvailableFinderService;
    private final VacancyNotificationService vacancyNotificationService;
    private final ApplicationProperties applicationProperties;

    public VacancyAvailableFinderJob(VacancyAvailableFinderService vacancyAvailableFinderService,
                                     VacancyNotificationService vacancyNotificationService,
                                     ApplicationProperties applicationProperties) {
        this.vacancyAvailableFinderService = vacancyAvailableFinderService;
        this.vacancyNotificationService = vacancyNotificationService;
        this.applicationProperties = applicationProperties;
    }

    @Scheduled(
            fixedDelayString = "${app.crawler.job.delay}",
            initialDelayString = "${app.crawler.job.initial-delay}"
    )
    public void execute() {

        String place = applicationProperties.getVacancy().getPlace();
        log.info("********");
        log.info("Staring job to search for available vacancies for place: '{}'...", place);

        Optional<Vacancy> vacancyOptional = vacancyAvailableFinderService.findByPlace(place);

        vacancyOptional.ifPresent(vacancy -> {
            log.info("Vacancy find for place: {}", place);
            log.info(vacancy.toString());
            vacancyNotificationService.send(vacancy);

            System.exit(-1);
        });

        log.info("End job.");
    }

}
