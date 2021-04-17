package io.github.lama06.lamamod.feature;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.lang.reflect.Field;
import java.util.Map;

public abstract class AbstractFeatureList {
    public JsonObject toJson() throws IllegalAccessException {
        JsonObject json = new JsonObject();

        for(Field field : getClass().getFields()) {
            AbstractFeature feature = (AbstractFeature) field.get(this);
            json.add(feature.getSerializedName(), feature.toJson());
        }

        return json;
    }

    public void fromJson(JsonObject json) throws IllegalAccessException {
        for(Map.Entry<String, JsonElement> entry : json.entrySet()) {
            String serializedName = entry.getKey();
            JsonElement value = entry.getValue();

            for (Field field : getClass().getFields()) {
                AbstractFeature feature = (AbstractFeature) field.get(this);

                if (feature.getSerializedName().equals(serializedName) && value.isJsonObject()) {
                    feature.fromJson(value.getAsJsonObject());
                    break;
                }
            }
        }
    }
}
