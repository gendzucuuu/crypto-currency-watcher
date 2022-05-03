package by.gendzucuuu.cryptocurrencywatcher.sheduler;

import by.gendzucuuu.cryptocurrencywatcher.connector.CoinloreConnector;
import by.gendzucuuu.cryptocurrencywatcher.model.Cryptocoin;
import by.gendzucuuu.cryptocurrencywatcher.service.CryptocoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class CryptocoinScheduler {
    private final CoinloreConnector coinloreConnector;

    private final CryptocoinService cryptocoinService;

    @Scheduled(fixedRate = 60000)
    public void updateCryptocoins() {
        List<Cryptocoin> cryptocoins = coinloreConnector.getAll(
                cryptocoinService.getAllCryptocoins().stream()
                .map(Cryptocoin::getId)
                .toList());

        cryptocoinService.updateAll(cryptocoins);
    }
}
