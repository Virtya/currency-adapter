package ru.ds.currencyadapter.dto.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ResponseMessageDto {
    String currencyName;
    String currencyDate;
    String currencyRate;
}
