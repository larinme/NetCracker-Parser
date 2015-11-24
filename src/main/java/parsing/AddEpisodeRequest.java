package parsing;

import java.util.HashSet;
import java.util.TreeSet;

/**
 * Created by LarinME on 24.11.2015 23:39.
 */
public class AddEpisodeRequest {

    private TreeSet<EpisodeRequest> episodeRequests;

    public TreeSet<EpisodeRequest> getEpisodeRequests() {
        return episodeRequests;
    }

    public void setEpisodeRequests(TreeSet<EpisodeRequest> episodeRequests) {
        this.episodeRequests = episodeRequests;
    }

    @Override
    public String toString() {
        return "AddEpisodeRequest{" +
                "episodeRequests=" + episodeRequests +
                '}';
    }
}
