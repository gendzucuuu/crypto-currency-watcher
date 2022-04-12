package by.gendzucuuu.cryptocurrencywatcher.util;

import lombok.SneakyThrows;

public class TestUtils {
    @SneakyThrows
    public static String getJsonAsString(String resourcePath) {
        return new String(TestUtils.class.getClassLoader()
                .getResourceAsStream(resourcePath)
                .readAllBytes());
    }
}
