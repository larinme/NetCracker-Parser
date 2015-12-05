package com.parser.response;

import java.util.Set;

/**
 * Wrapper of Parsing Request
 */
public class AddEpisodesRequest {

    private Set<EpisodeInformation> episodeInformations;

    public Set<EpisodeInformation> getEpisodeInformations() {
        return episodeInformations;
    }

    public void setEpisodeInformations(Set<EpisodeInformation> addEpisodeInformations) {
        this.episodeInformations = addEpisodeInformations;
    }

    @Override
    public String toString() {
        return "EpisodeInformation{" +
                "episodeInformations=" + episodeInformations +
                '}';
    }
}
