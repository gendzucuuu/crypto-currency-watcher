package by.gendzucuuu.cryptocurrencywatcher.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cryptocoin {
    @NotBlank
    private String id;
    @NotBlank
    private String symbol;
}