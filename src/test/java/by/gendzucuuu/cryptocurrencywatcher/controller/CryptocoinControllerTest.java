package by.gendzucuuu.cryptocurrencywatcher.controller;

import by.gendzucuuu.cryptocurrencywatcher.model.Cryptocoin;
import by.gendzucuuu.cryptocurrencywatcher.model.dto.response.CryptocoinResponseDto;
import by.gendzucuuu.cryptocurrencywatcher.service.CryptocoinService;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.SerializationFeature;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = CryptocoinController.class)
class CryptocoinControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CryptocoinService service;

    @Test
    void getAvailableCryptocoins() throws Exception {
        String btc = "BTC";
        String id = "90";

        CryptocoinResponseDto cryptocoinResponseDto = new CryptocoinResponseDto(id, btc);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String expectedResult = objectMapper.writeValueAsString(List.of(cryptocoinResponseDto));

        when(service.getAllCryptocoins())
                .thenReturn(List.of(getCryptocoin(id, btc)));

        String actualResult = mockMvc.perform(
                        get("/cryptocoins"))
//                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        JSONAssert.assertEquals(expectedResult, actualResult, JSONCompareMode.STRICT);
    }

    @NotNull
    private Cryptocoin getCryptocoin(String id, String BTC) {
        return new Cryptocoin() {{
            setId(id);
            setSymbol(BTC);
        }};
    }
}