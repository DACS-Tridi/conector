package com.dacs.conector.api.client;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Map;

@FeignClient(name = "spotifyAuthClient", url = "https://accounts.spotify.com")
public interface SpotifyAuthClient {

    @PostMapping(value = "/api/token", consumes = "application/x-www-form-urlencoded")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Map<String, Object> getToken(
        @RequestHeader("Authorization") String basicAuth,
        @RequestBody String body
    );
}
