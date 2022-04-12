package by.gendzucuuu.cryptocurrencywatcher.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cryptocoin {
    @NotBlank
    private String id;

    @NotBlank
    private String symbol;

    @NotBlank
    private BigDecimal price;
}