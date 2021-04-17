package io.github.lama06.lamamod.options;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import io.github.lama06.lamamod.events.EventResult;
import io.github.lama06.lamamod.util.ChatMessage;
import io.github.lama06.lamamod.util.Color;
import io.github.lama06.lamamod.util.Util;

public final class ColorOption extends AbstractOption<Color> {
    public ColorOption(String name, String description, String serializedName, String chatName, Color defaultValue) {
        super(name, description, serializedName, chatName, defaultValue);
    }

    @Override
    public JsonElement toJson() {
        JsonObject json = new JsonObject();
        Color color = getValue();

        json.add("red", new JsonPrimitive(color.red));
        json.add("green", new JsonPrimitive(color.green));
        json.add("blue", new JsonPrimitive(color.blue));

        return json;
    }

    @Override
    public void fromJson(JsonElement json) {
        JsonObject jsonObject = json.getAsJsonObject();

        JsonElement jsonRed = jsonObject.get("red");
        JsonElement jsonGreen = jsonObject.get("green");
        JsonElement jsonBlue = jsonObject.get("blue");

        if(Util.isNumber(jsonRed) && Util.isNumber(jsonGreen) && Util.isNumber(jsonBlue)) {
            int red = jsonObject.get("red").getAsInt();
            int green = jsonObject.get("green").getAsInt();
            int blue = jsonObject.get("blue").getAsInt();

            setValue(new Color(red, green, blue), false);
        }
    }

    @Override
    public EventResult onChatMessage(ChatMessage msg) {
        String[] args = msg.getArgs();

        if(args.length == 1 && args[0].equals("color")) {
            Util.sendMsgToPlayer("Die aktuelle Farbe ist: " + getValue().toString());
            return EventResult.CANCEL;
        } else if(args.length == 2 && args[0].equals("color")) {
            String colorName = args[1];

            if(Color.colors.containsKey(colorName)) {
                Color color = Color.colors.get(colorName);
                setValue(color);
                Util.sendMsgToPlayer("Die Farbe ist jetzt: " + color.toString());
            } else {
                Util.sendMsgToPlayer("Wir haben überall gesucht, aber diese Farbe nirgends wo gefunden :(");
            }

            return EventResult.CANCEL;
        } else if(args.length == 4 && args[0].equals("color")) {
            try {
                int red = Integer.parseInt(args[1]);
                int green = Integer.parseInt(args[2]);
                int blue = Integer.parseInt(args[3]);

                Color color = new Color(red, green, blue);
                setValue(color);
                Util.sendMsgToPlayer("Die Farbe ist jetzt: " + color.toString());
            } catch(NumberFormatException e) {
                Util.sendMsgToPlayer("Das ist keine Zahl");
            }

            return EventResult.CANCEL;
        }

        return EventResult.PASS;
    }
}
