package com.rateexchange.controller;

import com.rateexchange.model.BaseRate;
import com.rateexchange.model.Rate;
import com.rateexchange.service.RateServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.SchemaOutputResolver;

@RestController
@RequestMapping("/rates")
public class RateController {

    private static final Logger logger = LoggerFactory.getLogger(RateController.class);

    @Autowired
    RateServiceImpl rateService;

    @RequestMapping(value="/latest",method=RequestMethod.GET,
            produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getRates(@RequestParam(required = false, defaultValue = "USD") String base){
            logger.info("Fetching Rates Base " + base);
            BaseRate bRates = rateService.getAllByBase(base);
            logger.info("Fetching Rates Completed");
        return new ResponseEntity<>(bRates, HttpStatus.OK);
    }

    @RequestMapping(value="/{date}",method=RequestMethod.GET,
            produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getRateByDate(@PathVariable String date,
                                              @RequestParam(required = false, defaultValue = "USD") String base){
        logger.info("Fetching Rates dated:"+date);
        BaseRate rates = rateService.getRateByDate(base, date);
        logger.info("Fetching Rates Completed");
        return new ResponseEntity<>(rates, HttpStatus.OK);
    }

}
