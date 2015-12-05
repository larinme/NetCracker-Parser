package parsing.init;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import parsing.AbstractParser;
import parsing.response.AddEpisodeRequest;
import parsing.entities.Episode;

import java.util.Iterator;
import java.util.Set;

public abstract class Parser extends AbstractParser {

    /**
     * @return All available serials as Iterator<Element>
     */
    protected Iterator<Element> getIterator() {
        Document doc = getDocument(URL_SERIALS, null);
        Elements elements = doc.getElementsByClass("mid").get(0).getElementsByClass("bb_a");
        Iterator<Element> iterator = elements.iterator();
        return iterator;
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
    abstract protected Set<AddEpisodeRequest> getEpisodesInfo(Document page);
    abstract protected Episode parsingEpisode(Element episode);
    abstract public void parsing();
    abstract protected String getSerialName(String html);
    }
