package parsing.entities;

import java.util.regex.*;
/**
 * Created by Юрий on 02.12.2015.
 */
public class Studio {


    public final String URL;
    private final Pattern pattern;
    public final String charset;
    public final String name;
    public Studio(String url, String regex, String charset, String name){
        this.URL = url;
        pattern = Pattern.compile(regex);
        this.charset = charset;
        this.name = name;
    }

    public Pattern getPattern() {
        return pattern;
    }
}
