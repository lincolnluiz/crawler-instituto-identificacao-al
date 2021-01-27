package com.github.lincolnluiz.crawlerinstitutoidentificacaoal.notificaction;

import com.github.lincolnluiz.crawlerinstitutoidentificacaoal.ApplicationProperties;
import com.github.lincolnluiz.crawlerinstitutoidentificacaoal.domain.Vacancy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacancyNotificationService {

    public final static Logger log = LoggerFactory.getLogger(VacancyNotificationService.class);
    private static final String BREAK_LINE = "\n\r";

    private final ApplicationProperties applicationProperties;
    private final JavaMailSender javaMailSender;

    public VacancyNotificationService(ApplicationProperties applicationProperties,
                                      JavaMailSender javaMailSender) {
        this.applicationProperties = applicationProperties;
        this.javaMailSender = javaMailSender;
    }

    public void send(Vacancy vacancy) {
        String from = applicationProperties.getNotification().getFrom();
        List<String> to = applicationProperties.getNotification().getTo();

        log.info("Sending notification to {}", to);

        String messageText = "Vaga disponível: " + BREAK_LINE +
                "Local: " + vacancy.getPlace() + BREAK_LINE +
                "Periódo: " + vacancy.getPeriod() + BREAK_LINE +
                applicationProperties.getCrawler().getUrl();

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(to.toArray(new String[0]));
            message.setSubject("Vaga disponível para: " + vacancy.getPlace());
            message.setText(messageText);
            javaMailSender.send(message);

            log.info("Notifications sent");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
