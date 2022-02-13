package by.gendzucuuu.cryptocurrencywatcher.connector.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonSerialize
public record CoinloreInfoResponseDto(String id, String symbol, @JsonProperty("price_usd") BigDecimal priceUsd) {}

