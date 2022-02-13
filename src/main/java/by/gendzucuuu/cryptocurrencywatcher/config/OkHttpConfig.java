package by.gendzucuuu.cryptocurrencywatcher.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OkHttpConfig {

    @Bean
    public OkHttpClient getOkHttpClient() {
        return new OkHttpClient().newBuilder()
                .followRedirects(false)
                .build();
    }
}
