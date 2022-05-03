package by.gendzucuuu.crypto.currency.watcher.db.adapter.core.service;

import by.gendzucuuu.crypto.currency.watcher.db.adapter.model.response.UserCryptocoinResponseDto;

import java.util.List;

public interface UserCryptocoinService {
    UserCryptocoinResponseDto register(String username, String symbol);

    List<UserCryptocoinResponseDto> findAll();
}
