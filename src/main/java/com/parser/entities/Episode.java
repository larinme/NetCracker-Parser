package com.parser.entities;

import java.util.Date;

/**
 * Entity episode turned to sending as JSON
 */
public class Episode {


    /**
     * Parsed season number
     */
    private int seasonNumber;

    /**
     * Parsed episode number
     */
    private int episodeNumber;

    /**
     * Link to website
     * @return Link as string
     */
    private String link;

    /**
     * Date of parsing time
     * @return java.lang.Date object
     */
    private Date date;

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Episode episode = (Episode) o;

        if (seasonNumber != episode.seasonNumber) return false;
        if (episodeNumber != episode.episodeNumber) return false;
        if (!link.equals(episode.link)) return false;
        return date.equals(episode.date);

    }

    @Override
    public String toString() {
        return "Episode{" +
                "seasonNumber=" + seasonNumber +
                ", episodeNumber=" + episodeNumber +
                ", link='" + link + '\'' +
                ", date=" + date +
                '}';
    }

    @Override
    public int hashCode() {
        int result = seasonNumber;
        result = 31 * result + episodeNumber;
        result = 31 * result + link.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }

}
