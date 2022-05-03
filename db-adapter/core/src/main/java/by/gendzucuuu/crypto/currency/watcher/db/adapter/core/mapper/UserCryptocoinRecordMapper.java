package by.gendzucuuu.crypto.currency.watcher.db.adapter.core.mapper;

import by.gendzucuuu.crypto.currency.watcher.db.adapter.core.repository.CryptocoinRepository;
import by.gendzucuuu.crypto.currency.watcher.db.adapter.model.response.UserCryptocoinResponseDto;
import lombok.RequiredArgsConstructor;
import org.jooq.Record;
import org.jooq.RecordMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserCryptocoinRecordMapper implements RecordMapper<Record, UserCryptocoinResponseDto> {

    private final CryptocoinRepository cryptocoinRepository;

    @Override
    public UserCryptocoinResponseDto map(Record userCryptocoinRecord) {
        UserCryptocoinResponseDto userCryptocoinResponseDto = userCryptocoinRecord.into(UserCryptocoinResponseDto.class);
        userCryptocoinResponseDto.setCryptocoinSymbol(cryptocoinRepository.getById(userCryptocoinRecord.get("cryptocoin_id",
                Long.class)).getSymbol());
        return userCryptocoinResponseDto;
    }
}