import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Web {
    private String extractKey(String url){
        String keyValue = url.substring(url.lastIndexOf("/") + 1,url.lastIndexOf("."));
        return  keyValue;
    }
    public String[] getWbiKey(){
        String nav= HttpUtil.get("https://api.bilibili.com/x/web-interface/nav");
        Map entries = JSONUtil.parseObj(nav);
        Map wbi = (Map) ((Map) entries.get("data")).get("wbi_img");;
        String imgkey = extractKey((String) wbi.get("img_url"));
        String subKey = extractKey((String) wbi.get("sub_url"));
        String[] finalKey ={imgkey,subKey};
        return finalKey;
    }
    public String[] getGroupUser(String access_key,int tagid){
        String url = "https://api.bilibili.com/x/relation/tag?access_key=" + access_key + "&tagid=" + tagid;
        String group = HttpUtil.get(url);
        JSONArray data = (JSONArray) (JSONUtil.parseObj(group)).get("data");
        List<String> midArray = new ArrayList<>();
        for (Object userObj : data){
            JSONObject user = (JSONObject) userObj;
            String mid = user.get("mid").toString();
            midArray.add(mid);
        }
        String[] mid = new String[midArray.size()];
        midArray.toArray(mid);
        return mid;
    }
}


