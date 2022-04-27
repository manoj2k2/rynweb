package com.adi.productmanagement.service;

import com.adi.productmanagement.model.FixerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CurrencyExchangeService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${appSetting.exchange.currencyApi}")
    private String apiUrl;

    public float ConvertPrice(float givenPrice, String givenCurrency)
    {
        if(givenCurrency.trim().isEmpty()) {
            givenCurrency = "EUR";
            return  givenPrice;
        }

        var exchangeRate = getFlexResponse();
        var conversionRate = exchangeRate.rates.get(givenCurrency);

        return givenPrice/ conversionRate;
    }

    // Query exchange API
    public FixerResponse getFlexResponse() {
        var response = restTemplate.getForObject(apiUrl, FixerResponse.class);
        //  System.out.println(response.getBody());

        return response;
    }

}
