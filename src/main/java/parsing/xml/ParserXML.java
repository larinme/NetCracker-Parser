package parsing.xml;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import parsing.AbstractParser;

import javax.print.Doc;
import java.util.Iterator;

/**
 * Created by LarinME on 25.11.2015 18:17.
 */
public class ParserXML extends AbstractParser{


    public ParserXML(String url){
        this.URL = url;
    }
    public void parsing(){
        Document doc = getDocument(URL);
        Iterator<Element> iterator = doc.getElementsByTag("item").iterator();
        while(iterator.hasNext()){
            Element element = iterator.next();
            String titlesAndEpisodeNumbers = element.getElementsByTag("title").get(0).html();
            String currentSerialTitle = getSerialTitle(titlesAndEpisodeNumbers);
            String episodeTitle = getEpisodeTitle(titlesAndEpisodeNumbers);
            System.out.println(currentSerialTitle);

            //System.out.println(element.toString());
        }
    }
    private String getSerialTitle(String html){
        String title = null;
        title = html.substring(0, html.indexOf('('));
        return title;
    }
    private String getEpisodeTitle(String html){
        String title = null;
        title = html.replaceAll(".(.*)", "");
        return title;
    }

}
