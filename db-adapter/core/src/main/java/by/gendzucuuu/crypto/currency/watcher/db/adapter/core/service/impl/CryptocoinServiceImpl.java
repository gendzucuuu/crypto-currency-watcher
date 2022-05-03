package by.gendzucuuu.crypto.currency.watcher.db.adapter.core.service.impl;

import by.gendzucuuu.crypto.currency.watcher.db.adapter.core.repository.CryptocoinRepository;
import by.gendzucuuu.crypto.currency.watcher.db.adapter.core.service.CryptocoinService;
import by.gendzucuuu.crypto.currency.watcher.db.adapter.model.request.CryptocoinRequestDto;
import by.gendzucuuu.crypto.currency.watcher.db.adapter.model.response.CryptocoinResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CryptocoinServiceImpl implements CryptocoinService {
    private final CryptocoinRepository cryptocoinRepository;

    @Override
    public CryptocoinResponseDto create(CryptocoinRequestDto cryptocoinRequestDto) {
        return cryptocoinRepository.insert(cryptocoinRequestDto);
    }

    @Override
    public CryptocoinResponseDto findBySymbol(String symbol) {
        return cryptocoinRepository.getBySymbol(symbol);
    }

    @Override
    public List<CryptocoinResponseDto> findAll() {
        return cryptocoinRepository.getAll();
    }

    @Override
    public CryptocoinResponseDto updatePriceById(Long id, BigDecimal price) {
        return cryptocoinRepository.updatePriceById(id, price);
    }


}
