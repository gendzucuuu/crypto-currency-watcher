package by.gendzucuuu.cryptocurrencywatcher.controller;

import by.gendzucuuu.cryptocurrencywatcher.model.Cryptocoin;
import by.gendzucuuu.cryptocurrencywatcher.model.dto.response.CryptocoinResponseDto;
import by.gendzucuuu.cryptocurrencywatcher.service.CryptocoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class CryptocoinController {
    private final CryptocoinService cryptocoinService;

    @GetMapping(value = "/cryptocoins")
    public ResponseEntity<List<CryptocoinResponseDto>> getAvailableCryptocoins() {
        return ResponseEntity
                .ok()
                .body(cryptocoinService.getAllCryptocoins()
                        .stream()
                        .map(this::mapToDto)
                        .collect(Collectors.toList())
                );
    }

    private CryptocoinResponseDto mapToDto(Cryptocoin cryptocoin) {
        return new CryptocoinResponseDto(cryptocoin.getId(), cryptocoin.getSymbol());
    }
}
