package main;

import parsing.Studio;
import parsing.init.ParserLostFilm;
import parsing.xml.ParserXML;
 import java.lang.*;
/**
 * Created by LarinME on 25.11.2015 21:08.
 */
public class Main {
    public static void main(String[] args) {
        Studio[] sites = {//new Studio("http://lostfilm.tv/rss.xml", "S(\\d){2}E(\\d\\d)" , "Windows-1251", "</item>"),
                new Studio("http://www.newstudio.tv/rss.php", "Сезон (\\d){1,2}, Серия (\\d){1,2}", "utf-8", " <description>")};
        //new ParserLostFilm().parsing();
        for (Studio studio: sites) {
            new ParserXML(studio).parsing();
        }

    }
}
