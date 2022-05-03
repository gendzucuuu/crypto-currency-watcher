package by.gendzucuuu.crypto.currency.watcher.db.adapter.core.repository.impl;

import by.gendzucuuu.crypto.currency.watcher.db.adapter.core.exception.EntityNotFoundException;
import by.gendzucuuu.crypto.currency.watcher.db.adapter.core.repository.CryptocoinRepository;
import by.gendzucuuu.crypto.currency.watcher.db.adapter.core.tables.Cryptocoin;
import by.gendzucuuu.crypto.currency.watcher.db.adapter.model.request.CryptocoinRequestDto;
import by.gendzucuuu.crypto.currency.watcher.db.adapter.model.response.CryptocoinResponseDto;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Repository
public class CryptocoinRepositoryImpl implements CryptocoinRepository {
    private final DSLContext dsl;

    @Override
    public CryptocoinResponseDto insert(CryptocoinRequestDto cryptocoinRequestDto) {
        return Objects.requireNonNull(dsl.insertInto(Cryptocoin.CRYPTOCOIN)
                        .set(dsl.newRecord(Cryptocoin.CRYPTOCOIN, cryptocoinRequestDto))
                        .returning()
                        .fetchOne())
                .into(CryptocoinResponseDto.class);
    }

    @Override
    public CryptocoinResponseDto getById(Long id) {
        return dsl.select()
                .from(Cryptocoin.CRYPTOCOIN)
                .where(Cryptocoin.CRYPTOCOIN.ID.eq(id))
                .fetchOptional()
                .orElseThrow(() -> new EntityNotFoundException("Cryptocoin with id " + id + " not found"))
                .into(CryptocoinResponseDto.class);
    }

    @Override
    public CryptocoinResponseDto getBySymbol(String symbol) {
        return dsl.select()
                .from(Cryptocoin.CRYPTOCOIN)
                .where(Cryptocoin.CRYPTOCOIN.SYMBOL.eq(symbol))
                .fetchOptional()
                .orElseThrow(() -> new EntityNotFoundException("Cryptocoin with symbol  " + symbol + " not found"))
                .into(CryptocoinResponseDto.class);
    }

    @Override
    public List<CryptocoinResponseDto> getAll() {
        return dsl.select()
                .from(Cryptocoin.CRYPTOCOIN)
                .fetch()
                .into(CryptocoinResponseDto.class);
    }


    @Override
    public CryptocoinResponseDto updatePriceById(Long id, BigDecimal usdPrice) {
        return dsl.update(Cryptocoin.CRYPTOCOIN)
                .set(Cryptocoin.CRYPTOCOIN.USD_PRICE, usdPrice)
                .where(Cryptocoin.CRYPTOCOIN.ID.eq(id))
                .returning()
                .fetchOptional()
                .orElseThrow(() -> new EntityNotFoundException("Cryptocoin with id " + id + " not found"))
                .into(CryptocoinResponseDto.class);
    }

}
