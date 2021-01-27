package com.github.lincolnluiz.crawlerinstitutoidentificacaoal.domain;

public class Vacancy {

    private final String place;
    private final String period;

    public Vacancy(String place, String period) {
        this.place = place;
        this.period = period;
    }

    public String getPlace() {
        return place;
    }

    public String getPeriod() {
        return period;
    }

    @Override
    public String toString() {
        return "Vacancy{" +
                "place='" + place + '\'' +
                ", period='" + period + '\'' +
                '}';
    }
}
