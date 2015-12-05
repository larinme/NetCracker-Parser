package parsing.xml;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import parsing.*;
import parsing.entities.Episode;
import parsing.entities.Studio;
import parsing.response.AddEpisodeRequest;
import tokens.TokenManager;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by LarinME on 25.11.2015 18:17.
 */
public class ParserXML extends AbstractParser{

    private final Pattern pattern;
    private final String charset;
    private final String name;
    public ParserXML(Studio studio){
        this.URL = studio.URL;
        pattern = studio.getPattern();
        this.charset = studio.charset;
        name = studio.name;
    }
    public void parsing(){
        Document doc = getDocument(URL, charset);
        Iterator<Element> iterator = doc.getElementsByTag("item").iterator();
        Set<AddEpisodeRequest> setOfAddEpisodeRequest  = new HashSet<AddEpisodeRequest>();
        AddEpisodeRequest addEpisodeRequest = new AddEpisodeRequest();
        while(iterator.hasNext()){
            Element element = iterator.next();
            if (element.toString().toLowerCase().contains("сезон полностью"))
            {
                continue;
            }
            String titlesAndEpisodeNumbers = element.getElementsByTag("title").get(0).html();
            Episode episodeObj = new Episode();
            String currentSerialTitle = getSerialTitle(titlesAndEpisodeNumbers).trim();
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
            addEpisodeRequest.setSerialTitle(currentSerialTitle);
            addEpisodeRequest.setToken(TokenManager.getToken(name, currentSerialTitle));
            addEpisodeRequest.setEpisode(episodeObj);

            setOfAddEpisodeRequest.add(addEpisodeRequest);
        }
        prepareData(setOfAddEpisodeRequest);
    }
    private String getSerialTitle(String html){
        String title = null;
        html = html.replace("&lt;![CDATA[", "");
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
        Pattern pattern = Pattern.compile("<link>(.*)");
        Matcher matcher = pattern.matcher(item);
        while (matcher.find()){
            link = matcher.group(1).replaceAll(" ", "");
            break;
        }
        return link;
    }
}
