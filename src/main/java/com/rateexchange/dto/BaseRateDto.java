package com.rateexchange.dto;

import com.rateexchange.model.BaseRate;

import java.util.Map;

public class BaseRateDto extends BaseRate {

    private Map<String, Double> rates;

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}
