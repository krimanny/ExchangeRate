package com.rateexchange.service;

import com.rateexchange.dto.BaseRateDto;
import com.rateexchange.mapper.BaseRateMapper;
import com.rateexchange.mapper.RateExchangeMapper;
import com.rateexchange.model.BaseRate;
import com.rateexchange.model.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RateServiceImpl {

    @Autowired
    RateExchangeMapper rateExchangeMapper;

    @Autowired
    BaseRateMapper baseRateMapper;

    public BaseRateDto getAllByBase(String base){
        BaseRateDto baseRateDto = new BaseRateDto();
        BaseRate br = baseRateMapper.findAllByBase(base);
        if(br != null) {
            baseRateDto.setBase(br.getBase());
            baseRateDto.setDateAcquired(br.getDateAcquired());
            baseRateDto.setRateFrom(br.getRateFrom());
            List<Rate> er = rateExchangeMapper.findByBaseId(br.getBaseId());
            baseRateDto.setRates(er.stream().collect(Collectors.toMap(Rate::getCurrency, Rate::getRate)));
        }
        return baseRateDto;
    }

    public BaseRateDto getRateByDate(String base, String date){
        BaseRateDto baseRateDto = new BaseRateDto();
        BaseRate br = baseRateMapper.findAllByBaseAndDate(base, date);
        if(br != null) {
            baseRateDto.setBase(br.getBase());
            baseRateDto.setDateAcquired(br.getDateAcquired());
            baseRateDto.setRateFrom(br.getRateFrom());
            List<Rate> er = rateExchangeMapper.findByBaseId(br.getBaseId());
            baseRateDto.setRates(er.stream().collect(Collectors.toMap(Rate::getCurrency, Rate::getRate)));
        }
        return baseRateDto;
    }

}
