package leona.gsonutil;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.util.Map;
import leona.jsonutil.spec.JsonUtil;

/**
 *
 * @author Raghunath Nandyala
 */
public class GsonUtil extends JsonUtil<JsonArray, JsonObject> {

    private static GsonUtil i;

    public static GsonUtil instance() {
        if (i == null) {
            i = new GsonUtil();
        }
        return i;
    }
    private static final Gson gson = new Gson();

    private GsonUtil() {
    }

    @Override
    public JsonObject addToJsonObject(JsonObject jsonObject, String key, Object property) {
        JsonPrimitive prim = constructPrimitive(property);

        if (prim != null) {
            jsonObject.add(key, prim);
        } else {

            jsonObject.add(key, (JsonElement) formAJsonTreeFromObject(property));
        }

        return jsonObject;
    }

    @Override
    public JsonArray addToJsonArray(JsonArray jsonArray, Object element) {
        if (element instanceof JsonElement) {
            jsonArray.add((JsonElement) element);
        } else {
            JsonPrimitive prim = constructPrimitive(element);
            if (prim != null) {
                jsonArray.add(prim);
            } else {
                jsonArray.add((JsonElement) formAJsonTreeFromObject(element));
            }
        }
        return jsonArray;
    }

    public JsonPrimitive constructPrimitive(Object object) {
        JsonPrimitive prim = null;
        if (object instanceof String) {
            prim = new JsonPrimitive((String) object);
        } else if (object instanceof Number) {
            prim = new JsonPrimitive((Number) object);
        } else if (object instanceof Character) {
            prim = new JsonPrimitive((Character) object);
        } else if (object instanceof Boolean) {
            prim = new JsonPrimitive((Character) object);
        }
        return prim;
    }

    public JsonElement get(Object obj) {
        return gson.toJsonTree(obj);
    }

    @Override
    public JsonArray newJsonArray() {
        return new JsonArray();
    }

    @Override
    public JsonObject newJsonObject() {
        return new JsonObject();
    }

    @Override
    public JsonObject mergeInto(JsonObject det, JsonObject in) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object formAJsonTreeFromObject(Object javaBean) {
        return gson.toJsonTree(javaBean);
    }
    

}
