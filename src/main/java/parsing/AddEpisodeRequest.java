package parsing;

public class AddEpisodeRequest implements Comparable<AddEpisodeRequest> {

    private String token;

    private Episode episode;

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

        AddEpisodeRequest that = (AddEpisodeRequest) o;

        if (!token.equals(that.token)) return false;
        return episode.equals(that.episode);

    }

    @Override
    public int hashCode() {
        int result = token.hashCode();
        result = 31 * result + episode.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "EpisodeRequest{" +
                "token='" + token + '\'' +
                ", episode=" + episode +
                '}';
    }


    public int compareTo(AddEpisodeRequest o) {

        return this.episode.compareTo(o.getEpisode());
    }
}
