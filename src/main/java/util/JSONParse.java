package util;

import cn.hutool.json.JSONObject;
import exception.json.CookieException;

public class JSONParse {
    public static Object getValueByKey(JSONObject jsonObject, String targetKey) {
        for (String key : jsonObject.keySet()) {
            Object value = jsonObject.get(key);
            if (key.equals(targetKey)) {
                return value;
            }
            if (value instanceof JSONObject) {
                Object nestedValue = getValueByKey((JSONObject) value, targetKey);
                if (nestedValue != null) {
                    return nestedValue;
                }
            }
        }
        return null;
    }

    public static boolean isAvailable(JSONObject jsonObject) throws CookieException {
        String code = jsonObject.get("code").toString();
        if(code.equals("-404")){
            return false;
        }
        if (code.equals("-352")){
            throw new CookieException(jsonObject);
        }
        return true;
    }
}
