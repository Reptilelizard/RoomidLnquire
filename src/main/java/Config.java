import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.io.*;

public class Config {
    public ConfigValue<String,String> getValue(String fileLocation){
        String cookieValue = readCookie(fileLocation);
        return getCookie(cookieValue);
    }

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

    public ConfigValue getCookie(String cookieJson){
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
        return new ConfigValue(access_tokenValue,finalCookie);
    }

}

class ConfigValue<A,B>{
    public final A access_token;
    public final B cookie;

    public ConfigValue(A access_token,B cookie){
        this.access_token = access_token;
        this.cookie = cookie;
    }
}
