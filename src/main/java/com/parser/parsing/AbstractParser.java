package com.parser.parsing;

import com.parser.response.AddEpisodesRequest;
import com.parser.response.EpisodeInformation;
import com.parser.response.JsonRequest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

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
    protected void prepareData(Set<EpisodeInformation> episodeInformations){
        AddEpisodesRequest addEpisodesRequest = new AddEpisodesRequest();
        addEpisodesRequest.setEpisodeInformations(episodeInformations);
        if (JsonRequest.send(addEpisodesRequest)) {
            System.out.println("Everything is ok!");
        } else {
            System.out.println("Something wrong!");
        }
    }

}
