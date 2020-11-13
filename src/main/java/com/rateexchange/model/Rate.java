package com.rateexchange.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class Rate implements Serializable {

    @JsonIgnore
    private long rateId;
    private String currency;
    private double rate;
    @JsonIgnore
    private long baseId;

    public long getRateId() {
        return rateId;
    }

    public void setRateId(long rateId) {
        this.rateId = rateId;
    }

    public long getBaseId() {
        return baseId;
    }

    public void setBaseId(long baseId) {
        this.baseId = baseId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "rateId=" + rateId +
                ", currency='" + currency + '\'' +
                ", rate=" + rate +
                ", baseId=" + baseId +
                '}';
    }
}
