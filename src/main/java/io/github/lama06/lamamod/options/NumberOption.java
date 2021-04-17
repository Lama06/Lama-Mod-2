package io.github.lama06.lamamod.options;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import io.github.lama06.lamamod.events.EventResult;
import io.github.lama06.lamamod.util.ChatMessage;
import io.github.lama06.lamamod.util.Util;

public final class NumberOption extends AbstractOption<Integer> {
    public NumberOption(String name, String description, String serializedName, String chatName, Integer defaultValue) {
        super(name, description, serializedName, chatName, defaultValue);
    }

    @Override
    public JsonElement toJson() {
        return new JsonPrimitive(getValue());
    }

    @Override
    public void fromJson(JsonElement json) {
        if(Util.isNumber(json)) {
            setValue(json.getAsInt(), false);
        }
    }

    @Override
    public EventResult onChatMessage(ChatMessage msg) {
        String[] args = msg.getArgs();

        if(args.length == 1 && args[0].equals(getChatName())) {
            Util.sendMsgToPlayer(String.format("Der aktuelle Wert ist %s", getValue()));
            return EventResult.CANCEL;
        } else if(args.length == 2 && args[0].equals(getChatName())) {
            try {
                int number = Integer.parseInt(args[1]);
                setValue(number);
                Util.sendMsgToPlayer(String.format("Der neue Wert ist jetzt %s", getValue()));
            } catch(NumberFormatException e) {
                Util.sendMsgToPlayer("Bitte gib eine richtige Zahl an");
            }
            return EventResult.CANCEL;
        }

        return EventResult.PASS;
    }
}
