package com.example.demo;

import org.junit.jupiter.api.Test;

import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UrlAvailabilityTest {

    @Test
    void shouldAccessExampleCom() throws Exception {
        URL url = new URL("https://example.com");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000); // 5 saniye
        connection.setReadTimeout(5000);

        int responseCode = connection.getResponseCode();

        // 2xx ve 3xx kabul edilebilir
        assertTrue(responseCode == 400,
                "URL erişilemez! HTTP Code: " + responseCode);
    }
}