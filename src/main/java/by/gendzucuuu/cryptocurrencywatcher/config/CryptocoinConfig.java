package by.gendzucuuu.cryptocurrencywatcher.config;

import by.gendzucuuu.cryptocurrencywatcher.model.Cryptocoin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "config")
@EnableConfigurationProperties
public class CryptocoinConfig {

    @Getter
    @Setter
    @NotNull
    @Size(min = 1)
    private List<Cryptocoin> cryptocoins;
}