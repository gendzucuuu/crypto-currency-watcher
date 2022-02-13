package by.gendzucuuu.cryptocurrencywatcher.config;

import by.gendzucuuu.cryptocurrencywatcher.model.Cryptocoin;
import org.jetbrains.annotations.NotNull;
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
                        getCryptocoin("90", "BTC"),
                        getCryptocoin("80", "ETH"),
                        getCryptocoin("48543", "SOL")),
                actualCoins);

    }

    @NotNull
    private Cryptocoin getCryptocoin(String id, String BTC) {
        return new Cryptocoin() {{
            setId(id);
            setSymbol(BTC);
        }};
    }

}