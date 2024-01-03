import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;

import java.util.Map;

public class Json {
    private String extractKey(String url){
        String keyValue = url.substring(url.lastIndexOf("/") + 1,url.lastIndexOf("."));
        return  keyValue;
    }
    public String[] getKey(){
        String nvi= HttpUtil.get("https://api.bilibili.com/x/web-interface/nav");
        Map entries = JSONUtil.parseObj(nvi);
        Map wbi = (Map) ((Map) entries.get("data")).get("wbi_img");;
        String imgkey = extractKey((String) wbi.get("img_url"));
        String subKey = extractKey((String) wbi.get("sub_url"));
        String[] finalKey ={imgkey,subKey};
        return finalKey;
    }
}


