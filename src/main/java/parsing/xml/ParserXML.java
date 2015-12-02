package parsing.xml;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import parsing.*;

import javax.print.Doc;
import java.util.Date;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by LarinME on 25.11.2015 18:17.
 */
public class ParserXML extends AbstractParser{

    private final Pattern pattern;
    private final String charset;
    private final String nextTag;
    public ParserXML(Studio studio){
        this.URL = studio.URL;
        pattern = studio.getPattern();
        this.charset = studio.charset;
        this.nextTag = studio.nextTag;
    }
    public void parsing(){
        Document doc = getDocument(URL, charset);
        Iterator<Element> iterator = doc.getElementsByTag("item").iterator();
        AddEpisodeRequest addEpisodeRequest = new AddEpisodeRequest();
        TreeSet<AddEpisodeRequest> treeSet  = new TreeSet<AddEpisodeRequest>();
        while(iterator.hasNext()){
            Element element = iterator.next();
            String titlesAndEpisodeNumbers = element.getElementsByTag("title").get(0).html();
            Episode episodeObj = new Episode();
            String currentSerialTitle = getSerialTitle(titlesAndEpisodeNumbers);
            String episodeTitle = getEpisodeTitle(titlesAndEpisodeNumbers);
            int[] seasonAndEpisodeNum = getSeasonAndEpisodeNum(titlesAndEpisodeNumbers);
            int season = seasonAndEpisodeNum[0];
            int episode = seasonAndEpisodeNum[1];
            Date date = new Date();
            String link = getLink(element.toString());

            addEpisodeRequest = new AddEpisodeRequest();
            episodeObj.setEpisodeNumber(episode);
            episodeObj.setLink(link);
            episodeObj.setDate(date);
            episodeObj.setSeasonNumber(season);
            addEpisodeRequest.setEpisode(episodeObj);

            treeSet.add(addEpisodeRequest);

        }
        prepareData(treeSet);
    }
    private String getSerialTitle(String html){
        String title = null;
        title = html.substring(0, html.indexOf('('));
        return title;
    }
    private String getEpisodeTitle(String html){
        String title = null;
        title = new String(html.substring(html.indexOf('.') + 1));
        title = title.substring(0, title.indexOf('('));
        return title;
    }
    private int[] getSeasonAndEpisodeNum(String html){
        int season = 0;
        int episode = 0;
        Matcher matcher = pattern.matcher(html);
        while (matcher.find()) {
            season = Integer.parseInt(matcher.group(1));
            episode = Integer.parseInt(matcher.group(2));
        }
        return new int[]{season, episode};
    }
    private String getLink(String item){
        String link = null;
        System.out.println(item);
        link = item.substring(item.indexOf("<link>")+6, item.indexOf(nextTag) - 2);
        return link;
    }
}