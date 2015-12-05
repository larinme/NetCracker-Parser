package com.parser.parsing.init;
import com.parser.response.EpisodeInformation;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.parser.entities.Episode;
import com.parser.tokens.TokenManager;

import java.util.*;

/**
 * The class is used for allocating information which is already on the site.
 * You should call paring for one time.
 */
public final class ParserLostFilm extends Parser {


    public ParserLostFilm() {
        URL = "http://www.lostfilm.tv";
        URL_SERIALS = "http://www.lostfilm.tv/serials.php";
    }
    public void parsing() {
        Iterator<Element> serialsIterator = getIterator();
        Set<EpisodeInformation> Set = new HashSet<EpisodeInformation>();
        if (serialsIterator == null) {
            return;
        }
        while (serialsIterator.hasNext()) {
            try {
                Element serial = serialsIterator.next();
                currentSerialTitle = getSerialName(serial.html());
                Document doc = getDocument(URL + serial.attr("href"), "Windows-1251");
                if(!doc.toString().contains("Статус: снимается")){
                    continue;
                }
                Set<EpisodeInformation> tmp = getEpisodesInfo(doc);
                if (tmp != null) {
                    Set.addAll(tmp);
                }
            } catch (NullPointerException e) {

            }
        }
        prepareData(Set);
    }

    @Override
    protected String getSerialName(String html) {
        String name = null;
        name = html.substring(0, html.indexOf('<'));
        return name;
    }


    protected Episode parsingEpisode(Element episode){
        Episode parsedEpisode = new Episode();
        Integer episodeNum = parseEpisodeNum(episode, "t_episode_num", "label");
        if (episodeNum == null) {
            return null;
        }

        Elements dateAndSeasonAnsEpisode = episode.select("span:not(style)")
                .not("span[class=micro]")
                .not("span[style=color:#4b4b4b]")
                .not("span[style=line-height:11px;display:block]");
        Date date = new Date();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String[] strings = dateAndSeasonAnsEpisode.get(1).text().toString().split(" |-");
        int season;
        int episode_number;
        try {
            season = Integer.parseInt(strings[0]);
            episode_number = Integer.parseInt(strings[2]);
        } catch (NumberFormatException ex) {
            return null;
        }
        parsedEpisode.setDate(date);
        parsedEpisode.setEpisodeNumber(episode_number);
        parsedEpisode.setSeasonNumber(season);
        return parsedEpisode;
    }

   protected Set<EpisodeInformation> getEpisodesInfo(Document page) {
       Episode episode = new Episode();
       EpisodeInformation episodeInformation;
       Set<EpisodeInformation> hashSet = new HashSet<EpisodeInformation>();
       Elements seriesElement = page.getElementsByClass("t_row");
       for (int i = seriesElement.size() - 1; i >= 0; i--) {
           episode = parsingEpisode(seriesElement.get(i));
           if (episode == null) continue;
           episode.setLink(page.location());
           episodeInformation = new EpisodeInformation();
           episodeInformation.setEpisode(episode);
           episodeInformation.setToken(TokenManager.getToken("LostFilm", currentSerialTitle));
           hashSet.add(episodeInformation);
       }
        return hashSet;
    }
}
