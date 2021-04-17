package io.github.lama06.lamamod.shortcut;

import io.github.lama06.lamamod.feature.AbstractFeature;
import io.github.lama06.lamamod.events.EventResult;
import io.github.lama06.lamamod.options.BooleanOption;
import io.github.lama06.lamamod.util.ChatMessage;

public abstract class AbstractShortcut extends AbstractFeature {
    protected final BooleanOption enabled = new BooleanOption(
            "Shortcut Aktiviert",
            "Gibt an, ob der Shortcut ausgeführt werden soll",
            "enabled",
            "",
            true
    );

    public AbstractShortcut(String name, String description, String serializedName, String chatName) {
        super(name, description, serializedName, chatName);

        options.addOption(enabled);
    }

    protected abstract boolean isShortcut(ChatMessage msg);

    protected abstract void executeShortcut(ChatMessage msg);

    @Override
    public EventResult onChatMessage(ChatMessage msg) {
        if(enabled.getValue() && isShortcut(msg)) {
            executeShortcut(msg);
            return EventResult.CANCEL;
        } else {
            return super.onChatMessage(msg);
        }
    }
}
