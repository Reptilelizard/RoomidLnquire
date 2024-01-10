import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.List;

public class Web {
    private String extractKey(String url){
        return url.substring(url.lastIndexOf("/") + 1,url.lastIndexOf("."));
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
    public String[] getRoomId(String[] uid,String cookie) {
        Wbi wbi = new Wbi();
        List<String> idArray = new ArrayList<>();
        String[] wbi_img = getWbiKey();
        for (String mid : uid) {
            String wbi_key = wbi.count(wbi_img, mid);
            String url = "https://api.bilibili.com/x/space/wbi/acc/info?mid=" + mid + "&token=&platform=web&web_location=1550101" + wbi_key;
            String userList = HttpRequest.get(url).cookie(cookie).execute().body();
            JSONObject list = JSONUtil.parseObj(userList);
            String code = list.get("code").toString();
            if (code.equals("-352")) {//检查是否被B站风控
                System.out.println("Cookie已失效请重新获取");
                break;
            }
            if (code.equals("-404")) continue;//检查是否为空用户ID
            JSONObject data = (JSONObject) list.get("data");
            String live = data.get("live_room").toString();
            if (live.equals("null")) continue;//检查用户直播间信息是否为空
            JSONObject live_room = (JSONObject) data.get("live_room");
            String id = live_room.get("roomid").toString();
            idArray.add(id);
        }
        String[] roomId = new String[idArray.size()];
        idArray.toArray(roomId);
        return roomId;
    }
}


