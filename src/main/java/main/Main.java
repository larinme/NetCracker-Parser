package main;

import parsing.Studio;
import parsing.init.ParserLostFilm;
import parsing.xml.ParserXML;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by LarinME on 25.11.2015 21:08.
 */
public class Main {
    public static void main(String[] args) {

        Studio[] sites = {new Studio("http://lostfilm.tv/rss.xml", "S(\\d){2}E(\\d\\d)" , "Windows-1251", "</item>", "LostFilm"),
                new Studio("http://www.newstudio.tv/rss.php", "Сезон ([\\d]{1,2}), Серия ([\\d]{1,2})", "utf-8", " <description>", "NewStudio")};
        //new ParserLostFilm().parsing();
        for (Studio studio: sites) {
            new ParserXML(studio).parsing();
        }

        /*
        try {
            BufferedReader reader = new BufferedReader(new FileReader("tokensNewStudio.sql"));
                String serial = reader.readLine();
                String token = null;
                while (serial != null) {
                    serial = serial.substring(serial.lastIndexOf(',') + 3, serial.lastIndexOf('\''));
                    token = reader.readLine();
                    token = token.substring(token.indexOf("TOKEN_SEQ.NEXTVAL, \'") + 20 , token.lastIndexOf("\', (SEL"));
                    System.out.println("tokens.put(\"" + serial + "\", \"" + token+ "\");");
                    serial = reader.readLine();
                }

        }catch(Exception e){
            e.printStackTrace();
        }*/
    }
}
