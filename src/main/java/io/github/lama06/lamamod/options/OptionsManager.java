package io.github.lama06.lamamod.options;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.github.lama06.lamamod.events.EventResult;
import io.github.lama06.lamamod.events.MessageSentCallback;
import io.github.lama06.lamamod.util.ChatMessage;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class OptionsManager implements MessageSentCallback {
    private final Set<AbstractOption<?>> options = new HashSet<>();

    public void addOption(AbstractOption<?> option) {
        options.add(option);
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();

        for(AbstractOption<?> option : options) {
            json.add(option.getSerializedName(), option.toJson());
        }

        return json;
    }

    public void fromJson(JsonObject json) {
        for(Map.Entry<String, JsonElement> entry : json.entrySet()) {
            String name = entry.getKey();
            JsonElement value = entry.getValue();

            for(AbstractOption<?> option : options) {
                if(option.getSerializedName().equals(name)) {
                    option.fromJson(value);
                    break;
                }
            }
        }
    }

    @Override
    public EventResult onChatMessage(ChatMessage msg) {
        for(AbstractOption<?> option : options) {
            EventResult result = option.onChatMessage(msg);

            if(result == EventResult.CANCEL) {
                return result;
            }
        }

        return EventResult.PASS;
    }
}
