package parsing.init;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import parsing.AbstractParser;
import java.util.UUID;
import java.io.*;
/**
 * Created by Юрий on 02.12.2015.
 * @see parsing.AbstractParser
 */
public class ParserNewStudio extends AbstractParser {

    private File file;
    public ParserNewStudio(){
        URL = "http://www.newstudio.tv";
        file = new File("tokensNewStudio.sql");
    }
    public void parsing(){
       Document doc = getDocument(URL, "UTF-8");
    try {
        Elements elements = doc.getElementsByClass("accordion-inner").select("li").select("a");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
        bufferedWriter.write("INSERT INTO STUDIO (ID, LANGUAGE, NAME) VALUES(STUDIO_SEQ.NEXTVAL, \'RU\', \'NewStudio\');\n");
        for (Element element : elements) {
            UUID uuid = UUID.randomUUID();
            String el = element.toString();
            String title = el.substring(el.indexOf('>') + 1, el.lastIndexOf('<'));
            System.out.print("INSERT INTO SERIAL (ID, LANGUAGE, TITLE) VALUES (SERIAL_SEQ.NEXTVAL, \'EN\', \'" + title + "\');\n");
            System.out.print("INSERT INTO TOKEN (ID, TOKEN, SERIAL_ID, STUDIO_ID) VALUES (TOKEN_SEQ.NEXTVAL, \'" +
                    String.valueOf(uuid) + "\', " + "(SELECT ID FROM SERIAL WHERE TITLE=\'" + title + "\')" + ", (SELECT ID FROM STUDIO" +
                    " WHERE NAME=\'NewStudio\'));\n");
        }

    }
    catch (Exception e){

    }



    }

    public static void main(String[] args) {
        new ParserNewStudio().parsing();
    }
}
