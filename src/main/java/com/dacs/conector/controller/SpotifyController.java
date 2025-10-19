package com.dacs.conector.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dacs.conector.dto.AlbumDTO;
import com.dacs.conector.service.ISpotifyService;

@RestController
public class SpotifyController {

    @Autowired
    private ISpotifyService spotifyService;

    @GetMapping("/spotify/albums")
    public List<AlbumDTO> getAlbums() {
        return spotifyService.getAlbums();
    }
    
    @GetMapping("/spotify/albums/search")
    public List<AlbumDTO> searchAlbums(@RequestParam("q") String query) {
        return spotifyService.searchAlbums(query);
    }
    
    @GetMapping("/spotify/album")
    public AlbumDTO getAlbumById(@RequestParam("id") String id) {
        return spotifyService.getAlbumById(id);
    }
}
