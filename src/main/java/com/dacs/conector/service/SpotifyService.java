package com.dacs.conector.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.dacs.conector.api.client.SpotifyClient;
import com.dacs.conector.dto.spotify.SpotifyResponse;
import com.dacs.conector.dto.spotify.AlbumItem;
import com.dacs.conector.dto.AlbumDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SpotifyService implements ISpotifyService {

    @Autowired
    private SpotifyClient spotifyClient;

    @Autowired
    private SpotifyTokenProvider tokenProvider;

    @Override
    public List<AlbumDTO> getAlbums() {
        String token = tokenProvider.getAccessToken();

        SpotifyResponse response = spotifyClient.getNewReleases("Bearer " + token);

        return response.getAlbums().getItems()
            .stream()
            .map(this::mapToAlbumDTO)
            .collect(Collectors.toList());
    }
    
    public List<AlbumDTO> searchAlbums(String query) {
        String token = tokenProvider.getAccessToken();

        SpotifyResponse response = spotifyClient.searchAlbums("Bearer " + token, query, "album", 10);

        return response.getAlbums().getItems()
            .stream()
            .map(this::mapToAlbumDTO)
            .collect(Collectors.toList());
    }

    // TODO: Buscar un automapper o algo así
    private AlbumDTO mapToAlbumDTO(AlbumItem item) {
        AlbumDTO dto = new AlbumDTO();
        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setReleaseDate(item.getReleaseDate());
        dto.setTotalTracks(item.getTotalTracks());
        dto.setImageUrl(
            item.getImages() != null && !item.getImages().isEmpty()
                ? item.getImages().get(0).getUrl()
                : null
        );
        dto.setArtists(
            item.getArtists().stream()
                .map(artist -> artist.getName())
                .collect(Collectors.toList())
        );
        return dto;
    }
}
