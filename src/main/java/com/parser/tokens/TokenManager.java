package com.parser.tokens;

import com.parser.tokens.store.LostFilmTokenStore;
import com.parser.tokens.store.NewStudioTokenStore;
import com.parser.tokens.store.TokenStore;

import java.util.HashMap;

/**
 * Created by jedaka on 26.11.2015.
 */
public class TokenManager {

    /**
     * Studios store
     */
    private static HashMap<String, TokenStore> stores = new HashMap();

    static {
        stores.put("LostFilm", new LostFilmTokenStore());
        stores.put("NewStudio", new NewStudioTokenStore());
    }

    /**
     *
     * @param studio
     * @param serial
     * @return token as String
     */
    public static String getToken(String studio, String serial){
        return stores.get(studio).getToken(serial);
    }

    /**
     * Adding new TokenStrore by Studio key
     * @param studio
     * @param tokenStore
     */
    public static void addNewTokenStore(String studio, TokenStore tokenStore){
        stores.put(studio, tokenStore);
    }


}
