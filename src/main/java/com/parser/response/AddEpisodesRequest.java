package com.parser.response;

import java.util.Set;

/**
 * Wrapper of Parsing Request
 */
public class AddEpisodesRequest {

    private Set<EpisodeRequest> episodeRequests;

    public Set<EpisodeRequest> getEpisodeRequests() {
        return episodeRequests;
    }

    public void setEpisodeRequests(Set<EpisodeRequest> addEpisodeRequests) {
        this.episodeRequests = addEpisodeRequests;
    }

    @Override
    public String toString() {
        return "EpisodeRequest{" +
                "episodeRequests=" + episodeRequests +
                '}';
    }
}
