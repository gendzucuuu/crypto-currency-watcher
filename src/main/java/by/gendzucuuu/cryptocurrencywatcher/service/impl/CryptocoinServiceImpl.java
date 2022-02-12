package by.gendzucuuu.cryptocurrencywatcher.service.impl;

import by.gendzucuuu.cryptocurrencywatcher.config.CryptocoinConfig;
import by.gendzucuuu.cryptocurrencywatcher.model.Cryptocoin;
import by.gendzucuuu.cryptocurrencywatcher.service.CryptocoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CryptocoinServiceImpl implements CryptocoinService {
    private final CryptocoinConfig config;

    @Override
    public List<Cryptocoin> getAllCryptocoins() {
        return config.getCryptocoins();
    }
}
