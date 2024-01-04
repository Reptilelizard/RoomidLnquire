import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.util.*;

public class Web {
    private String extractKey(String url){
        String keyValue = url.substring(url.lastIndexOf("/") + 1,url.lastIndexOf("."));
        return  keyValue;
    }
    public String[] getWbiKey(){
        String nav= HttpUtil.get("https://api.bilibili.com/x/web-interface/nav");
        JSONObject entries = JSONUtil.parseObj(nav);
        JSONObject wbi = (JSONObject) ((JSONObject) entries.get("data")).get("wbi_img");
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
    public String[] getRoomId(String[] mid,String cookie) {
        Wbi wbi = new Wbi();
        List<String> idArray = new ArrayList<>();
        String[] wbi_img = getWbiKey();
        for (String m : mid) {
            String wbi_key = wbi.count(wbi_img, m);
            String url = "https://api.bilibili.com/x/space/wbi/acc/info?mid=" + m + "&token=&platform=web&web_location=1550101" + wbi_key;
            String userList = HttpRequest.get(url).cookie(cookie).execute().body();
            JSONObject list = JSONUtil.parseObj(userList);
            String code = list.get("code").toString();
            if (code.equals("-352")) continue;//检查是否被B站风控
            if (code.equals("-404")) continue;//检查是否为空用户ID
            JSONObject data = (JSONObject) list.get("data");
            String live = data.get("live_room").toString();
            if (live.equals("null")) continue;//检查用户是否拥有直播间信息
            JSONObject live_room = (JSONObject) data.get("live_room");
            String id = live_room.get("roomid").toString();
            idArray.add(id);

        }
        String[] roomId = new String[idArray.size()];
        idArray.toArray(roomId);
        return roomId;
    }
}


