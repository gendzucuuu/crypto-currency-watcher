package by.gendzucuuu.cryptocurrencywatcher.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cryptocoin {
    @NotBlank
    private String id;
    @NotBlank
    private String symbol;
}