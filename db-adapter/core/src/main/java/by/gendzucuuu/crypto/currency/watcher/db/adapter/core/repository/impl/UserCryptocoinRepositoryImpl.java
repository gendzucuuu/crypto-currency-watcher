package by.gendzucuuu.crypto.currency.watcher.db.adapter.core.repository.impl;

import by.gendzucuuu.crypto.currency.watcher.db.adapter.core.mapper.UserCryptocoinRecordMapper;
import by.gendzucuuu.crypto.currency.watcher.db.adapter.core.repository.UserCryptocoinRepository;
import by.gendzucuuu.crypto.currency.watcher.db.adapter.core.tables.Cryptocoin;
import by.gendzucuuu.crypto.currency.watcher.db.adapter.core.tables.UserCryptocoin;
import by.gendzucuuu.crypto.currency.watcher.db.adapter.model.response.CryptocoinResponseDto;
import by.gendzucuuu.crypto.currency.watcher.db.adapter.model.response.UserCryptocoinResponseDto;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserCryptocoinRepositoryImpl implements UserCryptocoinRepository {
    private final DSLContext dsl;
    private final UserCryptocoinRecordMapper userCryptocoinRecordMapper;

    @Override
    public Optional<Record> findByUsernameAndCryptocoinSymbol(String username, String symbol) {
        return dsl.select()
                .from(UserCryptocoin.USER_CRYPTOCOIN)
                .join(Cryptocoin.CRYPTOCOIN)
                .on(Cryptocoin.CRYPTOCOIN.ID.eq(UserCryptocoin.USER_CRYPTOCOIN.CRYPTOCOIN_ID))
                .where(UserCryptocoin.USER_CRYPTOCOIN.USERNAME.eq(username))
                .and(Cryptocoin.CRYPTOCOIN.SYMBOL.eq(symbol))
                .fetchOptional();
    }

    @Override
    public UserCryptocoinResponseDto registerUser(String username, CryptocoinResponseDto cryptocoin) {
        return Objects.requireNonNull(dsl.insertInto(UserCryptocoin.USER_CRYPTOCOIN)
                        .set(UserCryptocoin.USER_CRYPTOCOIN.USERNAME, username)
                        .set(UserCryptocoin.USER_CRYPTOCOIN.CRYPTOCOIN_ID, cryptocoin.getId())
                        .set(UserCryptocoin.USER_CRYPTOCOIN.CURRENT_USD_PRICE, cryptocoin.getUsdPrice())
                        .returning()
                        .fetchOne())
                .map(r -> {
                    UserCryptocoinResponseDto userCryptocoinResponseDto = r.into(UserCryptocoinResponseDto.class);
                    userCryptocoinResponseDto.setCryptocoinSymbol(cryptocoin.getSymbol());
                    return userCryptocoinResponseDto;
                });

    }

    @Override
    public UserCryptocoinResponseDto updateUser(String username, CryptocoinResponseDto cryptocoin) {
        return Objects.requireNonNull(dsl.update(UserCryptocoin.USER_CRYPTOCOIN)
                        .set(UserCryptocoin.USER_CRYPTOCOIN.CURRENT_USD_PRICE, cryptocoin.getUsdPrice())
                        .where(UserCryptocoin.USER_CRYPTOCOIN.USERNAME.eq(username))
                        .returning()
                        .fetchOne())
                .map(r -> {
                    UserCryptocoinResponseDto userCryptocoinResponseDto = r.into(UserCryptocoinResponseDto.class);
                    userCryptocoinResponseDto.setCryptocoinSymbol(cryptocoin.getSymbol());
                    return userCryptocoinResponseDto;
                });
    }

    @Override
    public List<UserCryptocoinResponseDto> getAll() {
        return dsl.select()
                .from(UserCryptocoin.USER_CRYPTOCOIN)
                .join(Cryptocoin.CRYPTOCOIN)
                .on(Cryptocoin.CRYPTOCOIN.ID.eq(UserCryptocoin.USER_CRYPTOCOIN.CRYPTOCOIN_ID))
                .fetch(userCryptocoinRecordMapper);

    }


}
