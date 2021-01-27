package com.github.lincolnluiz.crawlerinstitutoidentificacaoal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties({ApplicationProperties.class})
public class CrawlerInstitutoIdentificacaoAlApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                SpringApplication.run(CrawlerInstitutoIdentificacaoAlApplication.class, args);
    }

}
