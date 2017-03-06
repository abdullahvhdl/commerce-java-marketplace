package com.respodo.commerce.mapper;

import org.springframework.stereotype.Component;

import com.respodo.commerce.dto.CurrencyPriceDTO;
import com.respodo.commerce.repository.domain.CurrencyPrice;

@Component
public class CurrencyPriceMapper {

	public CurrencyPriceDTO toCurrencyPriceDTO(CurrencyPrice currencyPrice) {
		if(currencyPrice==null){
			return null;
		}
		CurrencyPriceDTO currencyPriceDTO=new CurrencyPriceDTO();
		currencyPriceDTO.setCurrency(currencyPrice.getCurrency());
		currencyPriceDTO.setPrice(currencyPrice.getPrice().doubleValue());
		return currencyPriceDTO;
	}

}
