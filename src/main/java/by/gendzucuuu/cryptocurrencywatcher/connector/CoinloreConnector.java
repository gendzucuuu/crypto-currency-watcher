package by.gendzucuuu.cryptocurrencywatcher.connector;

import by.gendzucuuu.cryptocurrencywatcher.model.Cryptocoin;
import by.gendzucuuu.cryptocurrencywatcher.model.News;

import java.io.IOException;
import java.util.List;

public interface CoinloreConnector {
    Cryptocoin getOne(String id) throws IOException;

    List<Cryptocoin> getAll(List<String> ids);
}
