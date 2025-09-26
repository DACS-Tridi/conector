package com.dacs.conector.service;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.dacs.conector.api.client.SpotifyAuthClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SpotifyTokenProvider {

    @Value("${spotify.client-id}")
    private String clientId;

    @Value("${spotify.client-secret}")
    private String clientSecret;

    @Autowired
    private SpotifyAuthClient authClient;

    private String cachedToken;
    private Instant expirationTime;

    public String getAccessToken() {
        if (cachedToken == null || Instant.now().isAfter(expirationTime)) {
            log.info("Getting Spotify Token...");
            String basicAuth = "Basic " + Base64.getEncoder().encodeToString(
                (clientId + ":" + clientSecret).getBytes(StandardCharsets.UTF_8)
            );

            Map<String, Object> tokenResponse = authClient.getToken(
                basicAuth,
                "grant_type=client_credentials"
            );

            cachedToken = (String) tokenResponse.get("access_token");
            Integer expiresIn = (Integer) tokenResponse.get("expires_in"); 
            expirationTime = Instant.now().plusSeconds(expiresIn); 

            log.info("Token valid for {} seconds", expiresIn);
        }
        return cachedToken;
    }
}
