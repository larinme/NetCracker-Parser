package main;

import parsing.entities.Studio;
import parsing.xml.ParserXML;

/**
 * Created by LarinME on 25.11.2015 21:08.
 */
public class Main {
    public static void main(String[] args) {

        Studio[] sites = {new Studio("http://lostfilm.tv/rss.xml", "S(\\d){2}E(\\d\\d)" , "Windows-1251", "LostFilm"),
                new Studio("http://www.newstudio.tv/rss.php", "Сезон ([\\d]{1,2}), Серия ([\\d]{1,2})", "utf-8", "NewStudio")};
        for (Studio studio: sites) {
            new ParserXML(studio).parsing();;
        }

    }
}
