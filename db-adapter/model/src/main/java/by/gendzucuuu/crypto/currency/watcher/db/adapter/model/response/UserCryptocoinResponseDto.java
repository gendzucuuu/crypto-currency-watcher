package by.gendzucuuu.crypto.currency.watcher.db.adapter.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCryptocoinResponseDto {
    private Integer id;
    private String username;
    private String cryptocoinSymbol;
    private BigDecimal currentUsdPrice;
}