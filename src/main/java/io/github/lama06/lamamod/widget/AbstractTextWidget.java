package io.github.lama06.lamamod.widget;

import io.github.lama06.lamamod.events.EventResult;
import io.github.lama06.lamamod.options.BooleanOption;
import io.github.lama06.lamamod.options.ColorOption;
import io.github.lama06.lamamod.util.ChatMessage;
import io.github.lama06.lamamod.util.Color;
import io.github.lama06.lamamod.util.Util;
import net.minecraft.client.util.math.MatrixStack;

public abstract class AbstractTextWidget extends AbstractWidget {
    protected BooleanOption showPrefix = new BooleanOption(
            "Prefix anzeigen",
            "Gibt an, ob der Prefix des Widgets angezeigt werdens soll",
            "prefix",
            "prefix",
            true
    );

    protected BooleanOption autoHide = new BooleanOption(
            "Widget bei leerem Text verstecken",
            "Versteckt das Widget, wenn es keinen Text anzeigt",
            "autoHide",
            "autoHide",
            true
    );

    protected ColorOption color = new ColorOption(
            "Farbe des Widgets",
            "Die Farbe des Widgets",
            "color",
            "color",
            Color.white
    );

    private final String prefix;

    public AbstractTextWidget(String name, String description, String serializedName, String chatName, String prefix) {
        super(name, description, serializedName, chatName);
        this.prefix = prefix;

        options.addOption(showPrefix);
        options.addOption(autoHide);
        options.addOption(color);
    }

    public abstract String getText();

    private String getWidgetText() {
        String text = getText();
        if(text.trim().isEmpty() && autoHide.getValue()) {
            return "";
        } else {
            return (showPrefix.getValue() ? prefix + ": " : "") + getText();
        }
    }

    @Override
    public void renderWidget(MatrixStack matrices) {
        client.textRenderer.draw(matrices, getWidgetText(), x.getValue(), y.getValue(),  color.getValue().toInt());
    }

    @Override
    public EventResult onChatMessage(ChatMessage msg) {
        String[] args = msg.getArgs();

        if(msg.getText().startsWith(getChatName()) && args.length == 1 && args[0].equals("tochat")) {
            Util.sendMsgToChat(getWidgetText());
            return EventResult.CANCEL;
        } else {
            return super.onChatMessage(msg);
        }
    }
}
