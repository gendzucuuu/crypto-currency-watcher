package by.gendzucuuu.crypto.currency.watcher.db.adapter.core.controller;

import by.gendzucuuu.crypto.currency.watcher.db.adapter.core.service.CryptocoinService;
import by.gendzucuuu.crypto.currency.watcher.db.adapter.model.request.CryptocoinRequestDto;
import by.gendzucuuu.crypto.currency.watcher.db.adapter.model.response.CryptocoinResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController("/cryptocoins")
public class CryptocoinController {
    private final CryptocoinService cryptoCoinService;

    @PostMapping
    public ResponseEntity<CryptocoinResponseDto> createCryptocoin(@RequestBody CryptocoinRequestDto cryptocoin) {
        return ResponseEntity.ok(cryptoCoinService.create(cryptocoin));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CryptocoinResponseDto> updateCryptocoinPrice(@PathVariable("id") Long id,
                                                                       @RequestBody CryptocoinRequestDto cryptocoin) {
        return ResponseEntity.ok(cryptoCoinService.updatePriceById(id, cryptocoin.getUsdPrice()));
    }

    @GetMapping("/{symbol}")
    public ResponseEntity<CryptocoinResponseDto> getCryptocoin(@PathVariable("symbol") String symbol) {
        return ResponseEntity.ok(cryptoCoinService.findBySymbol(symbol.toUpperCase().trim()));
    }
}
