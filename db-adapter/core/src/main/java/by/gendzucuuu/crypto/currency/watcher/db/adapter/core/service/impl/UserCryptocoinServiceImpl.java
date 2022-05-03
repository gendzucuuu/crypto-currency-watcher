package by.gendzucuuu.crypto.currency.watcher.db.adapter.core.service.impl;

import by.gendzucuuu.crypto.currency.watcher.db.adapter.core.repository.UserCryptocoinRepository;
import by.gendzucuuu.crypto.currency.watcher.db.adapter.core.service.CryptocoinService;
import by.gendzucuuu.crypto.currency.watcher.db.adapter.core.service.UserCryptocoinService;
import by.gendzucuuu.crypto.currency.watcher.db.adapter.model.response.CryptocoinResponseDto;
import by.gendzucuuu.crypto.currency.watcher.db.adapter.model.response.UserCryptocoinResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserCryptocoinServiceImpl implements UserCryptocoinService {
    private final UserCryptocoinRepository userCryptocoinRepository;
    private final CryptocoinService cryptocoinService;
    @Override
    public UserCryptocoinResponseDto register(String username, String symbol) {
        CryptocoinResponseDto cryptocoin = cryptocoinService.findBySymbol(symbol);
        if (userCryptocoinRepository.findByUsernameAndCryptocoinSymbol(username, symbol).isPresent()) {
            return userCryptocoinRepository.updateUser(username, cryptocoin);
        }

        return userCryptocoinRepository.registerUser(username, cryptocoin);

    }

    @Override
    public List<UserCryptocoinResponseDto> findAll() {
        return userCryptocoinRepository.getAll();
    }
}
