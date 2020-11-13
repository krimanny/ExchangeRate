package com.rateexchange;

import com.rateexchange.mapper.BaseRateMapper;
import com.rateexchange.mapper.RateExchangeMapper;
import com.rateexchange.model.BaseRate;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;

/**
 * This will be run everytime application start-up
 * To get real time must implicit run as Scheduler
 *
 * For testing purposes only
 */
@Configuration
public class LoadData {

    @Autowired
    BaseRateMapper baseRateMapper;

    @Autowired
    RateExchangeMapper rateExchangeMapper;

    private static final Logger logger = LoggerFactory.getLogger(LoadData.class);

    @EventListener(ApplicationReadyEvent.class)
    @Async("threadPoolTaskExecutor")
    public void loadExchangeRate() {
        final String exchangeRateIO = "https://exchangeratesapi.io/";
        logger.info("Start to Load data from "+ exchangeRateIO);

        String[] currencyArray = {"HRK","CHF", "MXN", "ZAR", "INR", "THB", "CNY", "AUD", "ILS", "KRW", "JPY", "PLN",
                             "GBP", "IDR", "HUF", "PHP", "TRY", "RUB", "HKD", "ISK", "EUR", "DKK", "CAD", "MYR",
                             "USD", "BGN", "NOK", "RON", "SGD", "CZK", "SEK", "NZD", "BRL"};
        for(String cur: currencyArray) {
            HttpGet request = new HttpGet("https://api.exchangeratesapi.io/latest?base="+cur);
            try (CloseableHttpClient httpClient = HttpClientBuilder.create().build();
                 CloseableHttpResponse response = httpClient.execute(request)) {

                String json = IOUtils.toString(response.getEntity().getContent());
                if(StringUtils.isNotBlank(json)) {
                    JSONObject object = new JSONObject(json);

                    String base = object.getString("base");
                    logger.info(base);
                    logger.info(object.getString("date"));
                    BaseRate baseRate = new BaseRate();
                    baseRate.setBase(base);
                    baseRate.setRateFrom(exchangeRateIO);
                    baseRateMapper.insertBaseRate(baseRate);
                    logger.info("BaseRate INSERTED :" + base);
                    JSONObject rates = object.getJSONObject("rates");
                    Set<String> keys = rates.keySet();

                    Iterator iterator = keys.iterator();
                    while (iterator.hasNext()) {
                        String currency = (String) iterator.next();
                        double rate = (double) rates.get(currency);
                        rateExchangeMapper.insertRate(rate, currency, baseRate.getBaseId());
                        logger.info(currency + ": " + rate + " INSERTED");
                    }
                }else{
                    logger.info("No Exchange Rate Data for :"+ cur);
                }

            } catch (IOException | JSONException | MyBatisSystemException e) {
                logger.error(e.getMessage());
                logger.error("Failed to Load Data");
            }
        }
        logger.info("Load Data Completed");
    }

    @Bean("threadPoolTaskExecutor")
    public Executor getAsyncExecutor() {
        ForkJoinPool pool = new ForkJoinPool(
                6, ForkJoinPool.defaultForkJoinWorkerThreadFactory, null, true);
        return pool;
    }
}
