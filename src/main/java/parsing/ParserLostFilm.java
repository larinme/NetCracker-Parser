package parsing;


import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Максим on 14.11.2015.
 */
public final class ParserLostFilm extends Parser{


    public ParserLostFilm(){
        URL = "http://www.lostfilm.tv";
        URL_SERIALS = "http://www.lostfilm.tv/serials.php";
    }
    public static void main(String[] args) {
        new ParserLostFilm().parsing();

    }

    /**
     * @returns The method returns nothing. It's do parsing.
     */

    public void parsing() {
        Iterator<Element> serialsIterator = getIterator();
        if (serialsIterator == null) {
            return;
        }
        while (serialsIterator.hasNext()) {
            try {
                Element serial = serialsIterator.next();
                getEpisodesInfo(serial.attr("href"));
            } catch (NullPointerException e) {
                continue;
            }
        }
    }

    /*
     * @returns all episode
     */
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
        Date date = convert(dateAndSeasonAnsEpisode.get(0).text());
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
    /**
     * URL?????????????????????????????
     * Method prepared episodes to sending on server
     * @param url_appendix is appendix to serial url
     */
   protected void getEpisodesInfo(String url_appendix) {
        Document doc = getDocument(URL + url_appendix);
        Episode episode = new Episode();
        Elements seriesElement = doc.getElementsByClass("t_row");
        for (int i = seriesElement.size()-1; i >= 0; i--) {
            episode = parsingEpisode(seriesElement.get(i));
            if (episode == null) continue;
            prepareData(episode);
        }

    }
}
