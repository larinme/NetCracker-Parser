package parsing.init;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import parsing.AddEpisodeRequest;
import parsing.Episode;
import tokens.TokenManager;

import java.util.Date;
import java.util.Iterator;
import java.util.TreeSet;

public final class ParserLostFilm extends Parser {


    public ParserLostFilm() {
        URL = "http://www.lostfilm.tv";
        URL_SERIALS = "http://www.lostfilm.tv/serials.php";
    }
    /**
     * @returns The method returns nothing. It's do parsing.
     */


    public void parsing() {
        Iterator<Element> serialsIterator = getIterator();
        TreeSet<AddEpisodeRequest> treeSet = new TreeSet<AddEpisodeRequest>();
        if (serialsIterator == null) {
            return;
        }

//        BufferedWriter bufferedWriter = null;
//        try {
//            bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\jedaka\\Desktop\\NetCracker-Parser\\src\\main\\resources\\lostFilmTokens.sql"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        while (serialsIterator.hasNext()) {
            try {
                Element serial = serialsIterator.next();

                currentSerialTitle = getSerialName(serial.html());

                Document doc = getDocument(URL + serial.attr("href"));

                if(!doc.toString().contains("Статус: снимается")){
                    continue;
                }

//                String uid = UUID.randomUUID().toString();
//                System.out.println("tokens.put(\"" + currentSerialTitle + "\", \"" + uid + "\");");
//                try {
//                    bufferedWriter.write("INSERT INTO SERIAL VALUES (SERIAL_SEQ.nextval, '', '" + currentSerialTitle + "');\n");
//                    bufferedWriter.write("INSERT INTO TOKEN VALUES(TOKEN_SEQ.nextval, '" + uid + "', (SELECT ID FROM SERIAL WHERE TITLE = '" + currentSerialTitle + "'), (SELECT ID FROM STUDIO WHERE NAME = 'LostFilm'));\n");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }


                TreeSet<AddEpisodeRequest> tmp = getEpisodesInfo(doc);
                if (tmp != null) {
                    treeSet.addAll(tmp);
                }
            } catch (NullPointerException e) {

            }
        }

//        try {
//            bufferedWriter.flush();
//            bufferedWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        prepareData(treeSet);
    }

    @Override
    protected String getSerialName(String html) {
        String name = null;
        name = html.substring(0, html.indexOf('<'));
        return name;
    }

    /*
         * @returns all episode
         */
    protected Episode parsingEpisode(Element episode){
        Episode parsedEpisode = new Episode();
        Integer episodeNum = parseEpisodeNum(episode, "t_episode_num", "label");
        if (episodeNum == null) {
            return null;
        }

        Elements dateAndSeasonAnsEpisode = episode.select("span:not(style)")
                .not("span[class=micro]")
                .not("span[style=color:#4b4b4b]")
                .not("span[style=line-height:11px;display:block]");
        Date date = new Date();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String[] strings = dateAndSeasonAnsEpisode.get(1).text().toString().split(" |-");
        int season;
        int episode_number;
        try {
            season = Integer.parseInt(strings[0]);
            episode_number = Integer.parseInt(strings[2]);
        } catch (NumberFormatException ex) {
            return null;
        }
        parsedEpisode.setDate(date);
        parsedEpisode.setEpisodeNumber(episode_number);
        parsedEpisode.setSeasonNumber(season);
        return parsedEpisode;
    }
    /**
     * URL?????????????????????????????
     * TOKEN!!!!!!!!!!!!!!!!!!!!!!!!!!
     *
     *
     * Method prepared episodes to sending on server
     * @param page is a serial html document
     */
   protected TreeSet<AddEpisodeRequest> getEpisodesInfo(Document page) {
       Episode episode = new Episode();
       AddEpisodeRequest addEpisodeRequest;
       TreeSet<AddEpisodeRequest> hashSet = new TreeSet<AddEpisodeRequest>();
       Elements seriesElement = page.getElementsByClass("t_row");
       for (int i = seriesElement.size() - 1; i >= 0; i--) {
           episode = parsingEpisode(seriesElement.get(i));
           if (episode == null) continue;
           episode.setLink(page.location());
           addEpisodeRequest = new AddEpisodeRequest();
           addEpisodeRequest.setEpisode(episode);
           addEpisodeRequest.setToken(TokenManager.getToken("LostFilm", currentSerialTitle));
           hashSet.add(addEpisodeRequest);
       }
        return hashSet;
    }
}
