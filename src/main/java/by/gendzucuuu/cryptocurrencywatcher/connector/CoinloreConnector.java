package by.gendzucuuu.cryptocurrencywatcher.connector;

import by.gendzucuuu.cryptocurrencywatcher.model.Cryptocoin;
import java.util.List;

public interface CoinloreConnector {
    Cryptocoin getOne(String id);

    List<Cryptocoin> getAll(final List<String> ids);
}
