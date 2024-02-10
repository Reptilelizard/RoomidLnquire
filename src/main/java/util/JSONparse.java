package util;

import cn.hutool.json.JSONObject;

public class JSONparse {
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
}
