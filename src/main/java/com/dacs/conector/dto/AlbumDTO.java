package com.dacs.conector.dto;

import lombok.Data;
import java.util.List;

@Data
public class AlbumDTO {
    private String id;
    private String name;
    private String releaseDate;
    private int totalTracks;
    private List<String> artists;
    private String imageUrl;
}
