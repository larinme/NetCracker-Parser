package parsing;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Date;
import java.util.Iterator;
import java.util.TreeSet;

public final class ParserLostFilm extends Parser{

    private String currentSerialTitle;


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
        TreeSet<AddEpisodeRequest> hashSet = new TreeSet<AddEpisodeRequest>();
        if (serialsIterator == null) {
            return;
        }
        while (serialsIterator.hasNext()) {
            try {
                Element serial = serialsIterator.next();
                currentSerialTitle = getSerialName(serial.html());
                hashSet.addAll(getEpisodesInfo(serial.attr("href")));
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        prepareData(hashSet);
    }

    @Override
    protected String getSerialName(String html) {
        String name = null;
        name = html.substring(0, html.indexOf('<'));
        return name;
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
    /**
     * URL?????????????????????????????
     * TOKEN!!!!!!!!!!!!!!!!!!!!!!!!!!
     *
     *
     * Method prepared episodes to sending on server
     * @param url_appendix is appendix to serial url
     */
   protected TreeSet<AddEpisodeRequest> getEpisodesInfo(String url_appendix) {
        Document doc = getDocument(URL + url_appendix);
        Episode episode = new Episode();
        AddEpisodeRequest addEpisodeRequest;
       TreeSet<AddEpisodeRequest> hashSet = new TreeSet<AddEpisodeRequest>();
       Elements seriesElement = doc.getElementsByClass("t_row");
        for (int i = seriesElement.size()-1; i >= 0; i--) {
            episode = parsingEpisode(seriesElement.get(i));
            if (episode == null) continue;
            episode.setLink(doc.location());
            addEpisodeRequest = new AddEpisodeRequest();
            if (currentSerialTitle.equals("Алькатрас")){
                addEpisodeRequest.setToken("573ca9af-71e0-4a22-a97e-2b1998118111");
            }
            addEpisodeRequest.setEpisode(episode);
            hashSet.add(addEpisodeRequest);
        }

        return hashSet;
    }
}
