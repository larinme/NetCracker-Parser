package main;

import parsing.init.ParserLostFilm;
import parsing.xml.ParserXML;

/**
 * Created by LarinME on 25.11.2015 21:08.
 */
public class Main {
    public static void main(String[] args) {
        String[] sites = {"http://lostfilm.tv/rss.xml"};
        new ParserLostFilm().parsing();
        //new ParserXML(sites[0]).parsing();
    }
}
