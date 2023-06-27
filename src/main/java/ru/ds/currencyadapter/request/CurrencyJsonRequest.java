package ru.ds.currencyadapter.request;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.ds.currencyadapter.dto.message.ResponseMessageDto;

import javax.json.JsonArray;
import javax.json.JsonObject;

@Component
public class CurrencyJsonRequest {

    public ResponseMessageDto sendCurrencyJsonRequest(String name, String date) {

        String url = "https://currate.ru/api/?get=rates&pairs=" + name +
                "RUB&date=" + date + "&key=014d70e0de08a0924b388b65212280f6";
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<JsonObject> response = restTemplate.exchange(url, HttpMethod.GET, null, JsonObject.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            JsonObject curJson = response.getBody();

            assert curJson != null;
            JsonArray currencies = curJson.getJsonArray("data");
            String curRate = currencies.getString(0);
            return new ResponseMessageDto(name, date, curRate);
        }

        return null;
    }
}
