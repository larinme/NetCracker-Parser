package com.parser.response;

import java.util.Set;

/**
 * Wrapper of Parsing Request
 */
public class AddEpisodesRequest {

    private Set<EpisodeInformation> episodesInformation;

    public Set<EpisodeInformation> getEpisodeInformations() {
        return episodesInformation;
    }

    public void setEpisodeInformations(Set<EpisodeInformation> addEpisodeInformations) {
        this.episodesInformation = addEpisodeInformations;
    }

    @Override
    public String toString() {
        return "EpisodeInformation{" +
                "episodeInformations=" + episodesInformation +
                '}';
    }
}
