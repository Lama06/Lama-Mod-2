package io.github.lama06.lamamod.options;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import io.github.lama06.lamamod.events.EventResult;
import io.github.lama06.lamamod.util.ChatMessage;
import io.github.lama06.lamamod.util.Util;

public final class BooleanOption extends AbstractOption<Boolean> {
    public BooleanOption(String name, String description, String serializedName, String chatName, Boolean defaultValue) {
        super(name, description, serializedName, chatName, defaultValue);
    }

    @Override
    public JsonElement toJson() {
        return new JsonPrimitive(getValue());
    }

    @Override
    public void fromJson(JsonElement json) {
        if(Util.isBoolean(json)) {
            setValue(json.getAsBoolean(), false);
        }
    }

    public void toggle() {
        if(getValue()) {
            setValue(false);
            Util.sendMsgToPlayer("Das Feature ist jetzt deaktiviert");
        } else {
            setValue(true);
            Util.sendMsgToPlayer("Das Feature ist jetzt aktiviert");
        }
    }

    @Override
    public EventResult onChatMessage(ChatMessage msg) {
        String[] args = msg.getArgs();

        if(args.length == 0 && getChatName().equals("")) {
            toggle();
            return EventResult.CANCEL;
        } else if(args.length == 1 && args[0].equals(getChatName())) {
            toggle();
            return EventResult.CANCEL;
        } else if(args.length == 2 && args[0].equals(getChatName())) {
            if(args[1].equals("on") || args[1].equals("an")) {
                setValue(true);
                Util.sendMsgToPlayer("Das Feature ist jetzt aktiviert");
                return EventResult.CANCEL;
            } else if(args[1].equals("off") || args[1].equals("aus")) {
                setValue(false);
                Util.sendMsgToPlayer("Das Feature ist jetzt deaktiviert");
                return EventResult.CANCEL;
            }
        }

        return EventResult.PASS;
    }
}
