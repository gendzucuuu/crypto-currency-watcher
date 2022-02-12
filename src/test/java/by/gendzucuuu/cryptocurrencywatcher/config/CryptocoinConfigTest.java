package by.gendzucuuu.cryptocurrencywatcher.config;

import by.gendzucuuu.cryptocurrencywatcher.model.Cryptocoin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

@SpringBootTest(classes = CryptocoinConfig.class)
@TestPropertySource(value = "classpath:cryptocoin-config-test.yml")
class CryptocoinConfigTest {

    @Autowired
    private CryptocoinConfig config;

    @Test
    void shouldReturnThreeCryptocoinsFromConfig() {
        List<Cryptocoin> actualCoins = config.getCryptocoins();
        Assertions.assertEquals(List.of(
                new Cryptocoin("90", "BTC"),
                new Cryptocoin("80", "ETH"),
                new Cryptocoin("48543", "SOL")),
                actualCoins);

    }
}