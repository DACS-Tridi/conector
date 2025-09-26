package com.dacs.conector.dto.spotify;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlbumItem {
    @JsonProperty("album_type")
    private String albumType;

    private List<Artist> artists;

    @JsonProperty("available_markets")
    private List<String> availableMarkets;

    private ExternalUrls externalUrls;
    private String href;
    private String id;
    private List<Image> images;
    private String name;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("release_date_precision")
    private String releaseDatePrecision;

    @JsonProperty("total_tracks")
    private int totalTracks;

    private String type;
    private String uri;
}
