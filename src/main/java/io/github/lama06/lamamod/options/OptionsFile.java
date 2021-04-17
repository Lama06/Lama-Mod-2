package io.github.lama06.lamamod.options;

import com.google.gson.*;
import io.github.lama06.lamamod.LamaMod;
import io.github.lama06.lamamod.util.Util;

import java.io.File;

public class OptionsFile {
    private final File optionsFile = new File("lama-mod-options.json");

    public void load() {
        try {
            if(!optionsFile.exists()) {
                optionsFile.createNewFile();
                Util.writeToFile(optionsFile, "{}");
            }
            String content = Util.readFromFile(optionsFile);

            JsonParser parser = new JsonParser();
            JsonObject json = parser.parse(content).getAsJsonObject();

            JsonElement widgetJson = json.get("widgets");
            JsonElement shortcutJson = json.get("shortcuts");

            if(widgetJson.isJsonObject()) {
                LamaMod.widgets.fromJson(widgetJson.getAsJsonObject());
            }

            if(shortcutJson.isJsonObject()) {
                LamaMod.shortcuts.fromJson(shortcutJson.getAsJsonObject());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            JsonObject json = new JsonObject();

            json.add("widgets", LamaMod.widgets.toJson());
            json.add("shortcuts", LamaMod.shortcuts.toJson());

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String serializedJson = gson.toJson(json);

            if(!optionsFile.exists()) {
                optionsFile.createNewFile();
            }
            Util.writeToFile(optionsFile, serializedJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
