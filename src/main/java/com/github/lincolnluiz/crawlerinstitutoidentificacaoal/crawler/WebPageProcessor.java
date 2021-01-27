package com.github.lincolnluiz.crawlerinstitutoidentificacaoal.crawler;

import com.github.lincolnluiz.crawlerinstitutoidentificacaoal.exception.WebPageProcessorException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class WebPageProcessor {

    public Document getDocumentFromUrl(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new WebPageProcessorException(e);
        }
    }

}
