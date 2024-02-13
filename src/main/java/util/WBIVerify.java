package util;

import cn.hutool.core.util.URLUtil;
import cn.hutool.crypto.SecureUtil;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;

public class WBIVerify {
    private static final int[] mixinKeyEncTab = new int[]{
            46, 47, 18, 2, 53, 8, 23, 32, 15, 50, 10, 31, 58, 3, 45, 35, 27, 43, 5, 49,
            33, 9, 42, 19, 29, 28, 14, 39, 12, 38, 41, 13, 37, 48, 7, 16, 24, 55, 40,
            61, 26, 17, 0, 1, 60, 51, 30, 4, 22, 25, 54, 21, 56, 59, 6, 63, 57, 62, 11,
            36, 20, 34, 44, 52
    };

    private static String getMixinKey(String imgkey) {

        StringBuilder key = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            key.append(imgkey.charAt(mixinKeyEncTab[i]));
        }
        return key.toString();
    }

    public static String getWbiKey(String key,String mid){
        String mixinKey = getMixinKey(key);
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("mid", mid);
        map.put("token","");
        map.put("platform", "web");
        map.put("web_location",1550101);
        map.put("wts", System.currentTimeMillis() / 1000);
        StringJoiner param = new StringJoiner("&");
        //排序 + 拼接字符串
        map.entrySet()
           .stream()
           .sorted(Map.Entry.comparingByKey())
           .forEach(entry -> param.add(entry.getKey() + "=" + URLUtil.encode(entry.getValue().toString())));
        String s = param + mixinKey;
        String wbiSign = SecureUtil.md5(s);
        String finalparm = "&w_rid=" + wbiSign + "&wts=" + map.get("wts");
        return  finalparm;
    }
}
