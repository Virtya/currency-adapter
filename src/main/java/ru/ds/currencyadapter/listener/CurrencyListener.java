package ru.ds.currencyadapter.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import ru.ds.currencyadapter.dto.message.RequestMessageDto;
import ru.ds.currencyadapter.dto.message.ResponseMessageDto;
import ru.ds.currencyadapter.request.CurrencyJsonRequest;

import static ru.ds.currencyadapter.config.ActiveMQConfig.RESPONSE_QUEUE;


@Component
public class CurrencyListener {

    private final JmsTemplate jmsTemplate;
    private final CurrencyJsonRequest currencyJsonRequest;

    public CurrencyListener(JmsTemplate jmsTemplate, CurrencyJsonRequest currencyJsonRequest) {
        this.jmsTemplate = jmsTemplate;
        this.currencyJsonRequest = currencyJsonRequest;
    }

    @JmsListener(destination = "request-queue")
    public void getCurrencyRequest(RequestMessageDto messageDto) {

        String currencyName = messageDto.getCurrencyName();
        String currencyDate = messageDto.getCurrencyDate();

        ResponseMessageDto responseMessageDto = currencyJsonRequest.sendCurrencyJsonRequest(currencyName, currencyDate);

        jmsTemplate.convertAndSend(RESPONSE_QUEUE, responseMessageDto);
    }
}
