package parsing.response;

import java.util.Set;

public class AddEpisodesRequest {

    private Set<AddEpisodeRequest> addEpisodeRequests;

    public Set<AddEpisodeRequest> getAddEpisodeRequests() {
        return addEpisodeRequests;
    }

    public void setAddEpisodeRequests(Set<AddEpisodeRequest> addAddEpisodeRequests) {
        this.addEpisodeRequests = addAddEpisodeRequests;
    }

    @Override
    public String toString() {
        return "AddEpisodeRequest{" +
                "addEpisodeRequests=" + addEpisodeRequests +
                '}';
    }
}
