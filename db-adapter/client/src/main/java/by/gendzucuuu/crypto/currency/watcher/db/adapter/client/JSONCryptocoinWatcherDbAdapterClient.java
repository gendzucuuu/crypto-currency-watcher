package by.gendzucuuu.crypto.currency.watcher.db.adapter.client;

import by.gendzucuuu.crypto.currency.watcher.db.adapter.model.request.CryptocoinRequestDto;
import by.gendzucuuu.crypto.currency.watcher.db.adapter.model.response.CryptocoinResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "jplaceholder", url = "https://jsonplaceholder.typicode.com/")
public interface JSONCryptocoinWatcherDbAdapterClient {

    @GetMapping(value = "/cryptcoins", produces = "application/json")
    List<CryptocoinResponseDto> getCryptocoinBySymbol();

    @PatchMapping(value = "/cryptcoins/{id}", produces = "application/json")
    CryptocoinResponseDto updateCryptocoinPriceById(@PathVariable("id") Long id,
                                               @RequestBody CryptocoinRequestDto cryptocoin);

    @PostMapping(value = "/cryptcoins", produces = "application/json")
    CryptocoinResponseDto createCryptocoin(@RequestBody CryptocoinRequestDto cryptocoin);

    @PostMapping(value = "/user-cryptocoins", produces = "application/json")
    CryptocoinResponseDto registerUserByUsernameAndSymbol(@RequestParam String username, @RequestParam String symbol);

    @GetMapping(value = "/user-cryptocoins", produces = "application/json")
    List<CryptocoinResponseDto> getAllUserCryptocoins();


}