package com.dacs.conector.service;

import java.util.List;
import com.dacs.conector.dto.AlbumDTO;

public interface ISpotifyService {
    List<AlbumDTO> getAlbums();
    List<AlbumDTO> searchAlbums(String query);
    AlbumDTO getAlbumById(String id);
}
