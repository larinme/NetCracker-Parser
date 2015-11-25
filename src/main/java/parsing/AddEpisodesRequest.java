package parsing;

import java.util.TreeSet;

public class AddEpisodesRequest {

    private TreeSet<AddEpisodeRequest> addEpisodeRequests;

    public TreeSet<AddEpisodeRequest> getAddEpisodeRequests() {
        return addEpisodeRequests;
    }

    public void setAddEpisodeRequests(TreeSet<AddEpisodeRequest> addAddEpisodeRequests) {
        this.addEpisodeRequests = addAddEpisodeRequests;
    }

    @Override
    public String toString() {
        return "AddEpisodeRequest{" +
                "addEpisodeRequests=" + addEpisodeRequests +
                '}';
    }
}
