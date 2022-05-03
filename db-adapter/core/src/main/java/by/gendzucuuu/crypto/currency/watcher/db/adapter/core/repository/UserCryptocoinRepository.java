package by.gendzucuuu.crypto.currency.watcher.db.adapter.core.repository;

import by.gendzucuuu.crypto.currency.watcher.db.adapter.model.response.CryptocoinResponseDto;
import by.gendzucuuu.crypto.currency.watcher.db.adapter.model.response.UserCryptocoinResponseDto;
import org.jooq.Record;

import java.util.List;
import java.util.Optional;

public interface UserCryptocoinRepository{
    Optional<Record> findByUsernameAndCryptocoinSymbol(String username, String symbol);

    UserCryptocoinResponseDto registerUser(String username, CryptocoinResponseDto cryptocoin);

    List<UserCryptocoinResponseDto> getAll();

    UserCryptocoinResponseDto updateUser(String username, CryptocoinResponseDto cryptocoin);
}
