package parsing;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class JsonRequest {

    public static void send(AddEpisodesRequest addEpisodesRequest) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String ans = mapper.writeValueAsString(addEpisodesRequest);
            String URL = "http://localhost:8080/add/episode";
            URL obj = new URL(URL);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);
            System.out.println("--------------------------------------------------");
            System.out.println(ans);
            OutputStream os = con.getOutputStream();
            os.write(ans.getBytes());
            os.flush();
            os.close();

            BufferedReader bis = new BufferedReader(new InputStreamReader(con.getInputStream()));
            while (bis.ready()){
                System.out.println(bis.readLine());
            }
        }catch (Exception e){
        }
    }

}
