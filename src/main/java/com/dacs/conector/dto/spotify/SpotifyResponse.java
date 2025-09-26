package com.dacs.conector.dto.spotify;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpotifyResponse {
    private Albums albums;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Albums {
        private String href;
        private List<AlbumItem> items;
        private int limit;
        private int offset;
        private int total;
        private String next;
        private String previous;
    }
}
