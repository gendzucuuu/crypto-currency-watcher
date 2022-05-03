package by.gendzucuuu.crypto.currency.watcher.db.adapter.core.repository;

import by.gendzucuuu.crypto.currency.watcher.db.adapter.model.request.CryptocoinRequestDto;
import by.gendzucuuu.crypto.currency.watcher.db.adapter.model.response.CryptocoinResponseDto;

import java.math.BigDecimal;
import java.util.List;

public interface CryptocoinRepository{
    CryptocoinResponseDto insert(CryptocoinRequestDto cryptocoinRequestDto);

    CryptocoinResponseDto getById(Long id);

    CryptocoinResponseDto getBySymbol(String symbol);

    List<CryptocoinResponseDto> getAll();

    CryptocoinResponseDto updatePriceById(Long id, BigDecimal usdPrice);

}
