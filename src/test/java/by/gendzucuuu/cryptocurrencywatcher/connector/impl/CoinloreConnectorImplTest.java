package by.gendzucuuu.cryptocurrencywatcher.connector.impl;

import by.gendzucuuu.cryptocurrencywatcher.config.JacksonConfig;
import by.gendzucuuu.cryptocurrencywatcher.connector.CoinloreConnector;
import by.gendzucuuu.cryptocurrencywatcher.model.Cryptocoin;
import by.gendzucuuu.cryptocurrencywatcher.util.TestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class CoinloreConnectorImplTest {

    public final OkHttpClient okHttpClient = new OkHttpClient();
    public final ObjectMapper objectMapper = new JacksonConfig().getObjectMapper();
    public static MockWebServer mockWebServer;
    public CoinloreConnector coinloreConnector;

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        HttpUrl url = mockWebServer.url("");
        coinloreConnector = new CoinloreConnectorImpl(url
                .toString(), objectMapper,
                okHttpClient);

    }


    @Test
    void getOne() {
        String jsonBtc = TestUtils.getJsonAsString("coinlore/connector/findone/btcresponce.json");

        MockResponse mockedResponse = new MockResponse()
                .setBody(jsonBtc) //Sample
                .addHeader("Content-Type", "application/json");
        mockWebServer.enqueue(mockedResponse);

        Cryptocoin cryptocoin = coinloreConnector.getOne("90");
        Assertions.assertEquals("90", cryptocoin.getId());
        Assertions.assertEquals("BTC", cryptocoin.getSymbol());
        Assertions.assertEquals(BigDecimal.valueOf(6465.26), cryptocoin.getPrice());
    }

    @Test
    void getAll() {
        String jsonBtc = TestUtils.getJsonAsString("coinlore/connector/findall/btcresponce.json");

        String jsonEth = TestUtils.getJsonAsString("coinlore/connector/findall/ethresponce.json");

        String jsonSol = TestUtils.getJsonAsString("coinlore/connector/findall/solresponce.json");

        Stream.of(jsonBtc, jsonEth, jsonSol)
                .map(json -> new MockResponse().setBody(json)
                        .addHeader("Content-Type", "application/json"))
                .forEach(mockResponse -> mockWebServer.enqueue(mockResponse));

        List<Cryptocoin> cryptocoins = coinloreConnector.getAll(List.of("90", "80", "48543"));

        List<Cryptocoin> expectedCryptocoins = List.of(
                new Cryptocoin("90", "BTC", BigDecimal.valueOf(6465.26)),
                new Cryptocoin("80", "ETH", BigDecimal.valueOf(2876.58)),
                new Cryptocoin("48543", "SOL", BigDecimal.valueOf(93.61)));

        assertThat(cryptocoins).contains(expectedCryptocoins.get(0))
                .contains(expectedCryptocoins.get(1))
                .contains(expectedCryptocoins.get(2));
    }

}