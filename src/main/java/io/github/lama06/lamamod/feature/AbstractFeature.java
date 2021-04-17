package io.github.lama06.lamamod.feature;

import com.google.gson.JsonObject;
import io.github.lama06.lamamod.events.EventResult;
import io.github.lama06.lamamod.events.MessageSentCallback;
import io.github.lama06.lamamod.options.OptionsManager;
import io.github.lama06.lamamod.util.ChatMessage;
import net.minecraft.client.MinecraftClient;

import java.util.Locale;

public abstract class AbstractFeature implements MessageSentCallback {
    private final String name;
    private final String description;
    private final String serializedName;
    private final String chatName;
    protected final OptionsManager options = new OptionsManager();
    protected final MinecraftClient client = MinecraftClient.getInstance();

    public AbstractFeature(String name, String description, String serializedName, String chatName) {
        this.name = name;
        this.description = description;
        this.serializedName = serializedName;
        this.chatName = chatName;

        MessageSentCallback.EVENT.register(this);
    }

    @Override
    public EventResult onChatMessage(ChatMessage msg) {
        if(msg.getText().startsWith(getChatName())) {
            return options.onChatMessage(msg);
        } else {
            return EventResult.PASS;
        }
    }

    public JsonObject toJson() {
        return options.toJson();
    }

    public void fromJson(JsonObject json) {
        options.fromJson(json);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSerializedName() {
        return serializedName;
    }

    public String getChatName() {
        return chatName.toLowerCase(Locale.ROOT);
    }
}
