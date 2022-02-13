package by.gendzucuuu.cryptocurrencywatcher.connector.impl;

import by.gendzucuuu.cryptocurrencywatcher.connector.CoinloreConnector;
import by.gendzucuuu.cryptocurrencywatcher.connector.dto.CoinloreInfoResponseDto;
import by.gendzucuuu.cryptocurrencywatcher.model.Cryptocoin;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CoinloreConnectorImpl implements CoinloreConnector {
    private final String baseUrl;
    private final ObjectMapper objectMapper;
    private final OkHttpClient okHttpClient;

    public CoinloreConnectorImpl(@Value("${coinlore.url}") String BASE_URL, ObjectMapper objectMapper, OkHttpClient okHttpClient) {
        this.baseUrl = BASE_URL;
        this.objectMapper = objectMapper;
        this.okHttpClient = okHttpClient;
    }

    @SneakyThrows
    @Override
    public Cryptocoin getOne(final String id) {
        HttpUrl.Builder urlBuilder
                = HttpUrl.parse(baseUrl).newBuilder();
        urlBuilder.addQueryParameter("id", id);

        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();
        ResponseBody responseBody = okHttpClient.newCall(request).execute().body();
        List<CoinloreInfoResponseDto> coinloreInfoResponseDtos = objectMapper.readValue(responseBody.string(),
                new TypeReference<List<CoinloreInfoResponseDto>>() {});


        return mapToCryptocoin(coinloreInfoResponseDtos
                .get(0));
    }

    @Override
    public List<Cryptocoin> getAll(final List<String> ids) {
        return null;
    }

    private Cryptocoin mapToCryptocoin(final CoinloreInfoResponseDto coinloreInfoResponseDto) {
        return Cryptocoin.builder()
                .id(coinloreInfoResponseDto.id())
                .symbol(coinloreInfoResponseDto.symbol())
                .price(coinloreInfoResponseDto.priceUsd())
                .build();
    }
}
