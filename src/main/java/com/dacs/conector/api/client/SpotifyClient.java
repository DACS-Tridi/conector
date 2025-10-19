package com.dacs.conector.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.dacs.conector.dto.spotify.AlbumItem;
import com.dacs.conector.dto.spotify.SpotifyResponse;

@FeignClient(name = "spotifyClient", url = "https://api.spotify.com/v1")
public interface SpotifyClient {

    @GetMapping("/browse/new-releases")
    SpotifyResponse getNewReleases(@RequestHeader("Authorization") String bearerToken);

    @GetMapping("/search")
    SpotifyResponse searchAlbums(
        @RequestHeader("Authorization") String bearerToken,
        @RequestParam("q") String query,
        @RequestParam("type") String type,
        @RequestParam("limit") int limit
    );

    @GetMapping("/albums/{id}")
    AlbumItem getAlbumById(
        @RequestHeader("Authorization") String bearerToken,
        @PathVariable("id") String id
    );
}
