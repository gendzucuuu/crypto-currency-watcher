package by.gendzucuuu.cryptocurrencywatcher.config;

import by.gendzucuuu.cryptocurrencywatcher.model.Cryptocoin;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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