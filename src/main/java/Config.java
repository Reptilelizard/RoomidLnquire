import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class Config {

    public String readCookie(String fileLocation){
        String cookie = null;
        try {
            BufferedReader cookies = new BufferedReader(new FileReader(fileLocation));
            StringBuilder content = new StringBuilder();
            while ((cookie = cookies.readLine()) != null){
                content.append(cookie);
            }
            cookie = content.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cookie;
    }

    public Map<String, String> getCookie(String cookieJson){
        JSONObject entries = new JSONUtil().parseObj(cookieJson);
        JSONObject cookies_info = (JSONObject) entries.get("cookie_info");
        JSONArray cookies = (JSONArray) cookies_info.get("cookies");
        String finalCookie = "";
        for (Object cookieValue : cookies){
            JSONObject cookie = (JSONObject) cookieValue;
            finalCookie += cookie.get("name") + "=" + cookie.get("value") + ";" + " ";
        }
        JSONObject token_info = (JSONObject) entries.get("token_info");
        String access_tokenValue = (String) token_info.get("access_token");
        Map<String,String> cookie = new HashMap<>();
        cookie.put("access_token",access_tokenValue);
        cookie.put("cookie",finalCookie);
        return cookie;
    }

}
