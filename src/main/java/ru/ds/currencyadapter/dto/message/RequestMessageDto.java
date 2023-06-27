package ru.ds.currencyadapter.dto.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class RequestMessageDto {
    private String currencyName;

    private String currencyDate;
}
