package by.gendzucuuu.cryptocurrencywatcher.connector.impl;

import by.gendzucuuu.cryptocurrencywatcher.config.JacksonConfig;
import by.gendzucuuu.cryptocurrencywatcher.connector.CoinloreConnector;
import by.gendzucuuu.cryptocurrencywatcher.model.Cryptocoin;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.math.BigDecimal;

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
    void getOne() throws IOException {
        String jsonBtc = new String(getClass().getClassLoader()
                .getResourceAsStream("coinlore/connector/btcresponce.json")
                .readAllBytes());
        ;
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
    }
}