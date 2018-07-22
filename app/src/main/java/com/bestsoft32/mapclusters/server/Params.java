package com.bestsoft32.mapclusters.server;

import java.util.HashMap;
import java.util.Map;

public class Params {
    public static Map<String, String> showData(String userId, String apiKey) {
        Map<String, String> param = new HashMap<>();
        param.put("userId", userId);
        param.put("apiKey", apiKey);
        return param;
    }

}
