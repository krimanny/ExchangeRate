package com.rateexchange.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;

public class BaseRate implements Serializable {

    @JsonIgnore
    private long baseId;
    private String base;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Manila")
    private Date dateAcquired;
    private String rateFrom;

    public long getBaseId() {
        return baseId;
    }

    public void setBaseId(long baseId) {
        this.baseId = baseId;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Date getDateAcquired() {
        return dateAcquired;
    }

    public void setDateAcquired(Date dateAcquired) {
        this.dateAcquired = dateAcquired;
    }

    public String getRateFrom() {
        return rateFrom;
    }

    public void setRateFrom(String rateFrom) {
        this.rateFrom = rateFrom;
    }


    @Override
    public String toString() {
        return "BaseRate{" +
                "baseId=" + baseId +
                ", base='" + base + '\'' +
                ", dateAcquired=" + dateAcquired +
                ", rateFrom='" + rateFrom + '\'' +
                '}';
    }
}
