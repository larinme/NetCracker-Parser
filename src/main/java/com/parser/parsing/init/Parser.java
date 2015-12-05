package com.parser.parsing.init;

import com.parser.entities.Episode;
import com.parser.parsing.AbstractParser;
import com.parser.response.EpisodeInformation;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Iterator;
import java.util.Set;

/**
 * Abstract class "Parser" is to overriding methods to initialization parse of website
 */
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
    *  Method implements  com.parser.parsing of sites where className contains in tagContent episode number
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
    /**
     * Method prepared episodes to sending on server
     * @param page is a serial html document
     * @return Set of episodes
     */
    abstract protected Set<EpisodeInformation> getEpisodesInfo(Document page);
    /**
     * @param episode
     * @returns Episode as object
     */
    abstract protected Episode parsingEpisode(Element episode);

    /**
     * Entry point of parsing.
     * Method extracts necessary information and send as JSON to server
     */
    abstract public void parsing();

    /**
     * Method searches serial name in html code
     * @param html as String
     * @return String object of title
     */
    abstract protected String getSerialName(String html);
    }
