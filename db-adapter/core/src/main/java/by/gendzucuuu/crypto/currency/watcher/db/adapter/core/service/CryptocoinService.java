package by.gendzucuuu.crypto.currency.watcher.db.adapter.core.service;

import by.gendzucuuu.crypto.currency.watcher.db.adapter.model.request.CryptocoinRequestDto;
import by.gendzucuuu.crypto.currency.watcher.db.adapter.model.response.CryptocoinResponseDto;

import java.math.BigDecimal;
import java.util.List;

public interface CryptocoinService {
    CryptocoinResponseDto create(CryptocoinRequestDto cryptocoinRequestDto);

    CryptocoinResponseDto findBySymbol(String symbol);
    List<CryptocoinResponseDto> findAll();

    CryptocoinResponseDto updatePriceById(Long id, BigDecimal price);



}
