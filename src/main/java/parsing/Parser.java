package parsing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Iterator;
import java.util.TreeSet;

public abstract class Parser {

    public static String URL;
    public static String URL_SERIALS;

    public static Document getDocument(String URL) {
        try {
            return Jsoup.connect(URL).get();
        } catch (IOException e) {
            return null;
        }
    }
    /**
     * @return All available serials as Iterator<Element>
     */
    protected Iterator<Element> getIterator() {
        Document doc = getDocument(URL_SERIALS);
        Elements elements = doc.getElementsByClass("mid").get(0).getElementsByClass("bb_a");
        Iterator<Element> iterator = elements.iterator();
        return iterator;
    }

    protected void prepareData(TreeSet<AddEpisodeRequest> addEpisodeRequests){
        AddEpisodesRequest addEpisodesRequest = new AddEpisodesRequest();
        addEpisodesRequest.setAddEpisodeRequests(addEpisodeRequests);
        JsonRequest.send(addEpisodesRequest);
    }


    /**
    *  Method implements  parsing of sites where className contains in tagContent episode number
    *  The common implementation to LostFilm and Kubik3
    *  @param episode is DOM of the episode.
    *  @param className is attribute which identify position of episode number
    *  @param tagContent specify especially edging of number. If number is in tag with className without
    *          edging then send null.
    */
    protected Integer parseEpisodeNum(Element episode, String className, String tagContent) {
        Elements elements = null;
        if (tagContent != null) {
            elements = episode.getElementsByClass(className).select(tagContent);
        }else{
            elements = episode.getElementsByClass(className);
        }
        if (!elements.isEmpty()) return null;
        try {
            return Integer.parseInt(episode.getElementsByClass(className).text());
        } catch (NumberFormatException e) {
            return Integer.parseInt(episode.getElementsByClass(className).text().split("[- ]")[0]);
        }
    }
    abstract protected TreeSet<AddEpisodeRequest> getEpisodesInfo(String url_appendix);
    abstract protected Episode parsingEpisode(Element episode);
    abstract public void parsing();
    }
