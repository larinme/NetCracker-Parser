package com.parser.response;


import com.parser.entities.Episode;

/**
 * Wrapper of parsed episode
 */
public class EpisodeRequest {

    /**
     * Token to identification serial
     */
    private String token;

    /**
     * Parsed data
     */
    private Episode episode;

    @Override
    public String toString() {
        return "EpisodeRequest{" +
                "token='" + token + '\'' +
                ", episode=" + episode +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Episode getEpisode() {
        return episode;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EpisodeRequest that = (EpisodeRequest) o;

        if (!token.equals(that.token)) return false;
        return episode.equals(that.episode);

    }

    @Override
    public int hashCode() {
        int result = 1;
        if (token != null){
            result = token.hashCode();
        }
        result = 31 * result + episode.hashCode();
        return result;
    }

}
