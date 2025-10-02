package com.dacs.conector.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.dacs.conector.dto.spotify.SpotifyResponse;

@FeignClient(
		name = "spotifyClient", 
		url = "${feign.client.config.spotifyClient.url}"
		)
public interface SpotifyClient {

    @GetMapping("/browse/new-releases")
    SpotifyResponse getNewReleases(
        @RequestHeader("Authorization") String authorization,
        @RequestParam(value = "limit", defaultValue = "10") int limit
    );

    default SpotifyResponse getNewReleases(String authorization) {
        return getNewReleases(authorization, 10);
    }
    
    @GetMapping("/search")
    SpotifyResponse searchAlbums(
            @RequestHeader("Authorization") String authorization,
            @RequestParam("q") String query,
            @RequestParam("type") String type,
            @RequestParam(value = "limit", defaultValue = "10") int limit
        );

        default SpotifyResponse searchAlbums(String authorization, String query) {
            return searchAlbums(authorization, query, "album", 10);
        }
}
