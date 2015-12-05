package parsing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import parsing.response.AddEpisodeRequest;
import parsing.response.AddEpisodesRequest;
import parsing.response.JsonRequest;

import java.io.IOException;
import java.net.URL;
import java.util.Set;

/**
 * Created by LarinME on 25.11.2015 18:13.
 */
abstract public class AbstractParser  {
    public  String URL;
    public  String URL_SERIALS;
    protected String currentSerialTitle;

    public static Document getDocument(String URL, String charset) {
        try {
          //
            if(charset == null)
            {
                return Jsoup.connect(URL).get();
            }else {
                return Jsoup.parse(new URL(URL).openStream(), charset, URL);
            }
        } catch (IOException e) {
            return null;
        }
    }
    protected void prepareData(Set<AddEpisodeRequest> addEpisodeRequests){
        AddEpisodesRequest addEpisodesRequest = new AddEpisodesRequest();
        addEpisodesRequest.setAddEpisodeRequests(addEpisodeRequests);
        JsonRequest.send(addEpisodesRequest);
    }

}
