package com.parser.entities;

import java.util.regex.Pattern;
/**
 * Created by Юрий on 02.12.2015.
 */
public class Studio {


    /**
     * RSS file URL
     */
    public final String URL;
    /**
     * Pattern of regular expression to searching season and episode number
     * @see Episode
     */
    private final Pattern pattern;
    /**
     * Charset connection by Jsoup library
     */
    public final String charset;
    /**
     * Studio name
     */
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
