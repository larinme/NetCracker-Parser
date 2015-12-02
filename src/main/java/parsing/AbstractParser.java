package parsing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.TreeSet;

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
    protected void prepareData(TreeSet<AddEpisodeRequest> addEpisodeRequests){
        AddEpisodesRequest addEpisodesRequest = new AddEpisodesRequest();
        addEpisodesRequest.setAddEpisodeRequests(addEpisodeRequests);
        JsonRequest.send(addEpisodesRequest);
    }

}
