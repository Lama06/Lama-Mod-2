package io.github.lama06.lamamod.shortcuts;

import io.github.lama06.lamamod.shortcut.AbstractShortcut;
import io.github.lama06.lamamod.util.ChatMessage;
import io.github.lama06.lamamod.util.Util;

import java.util.HashMap;
import java.util.Map;

public final class OtherShortcuts extends AbstractShortcut {
    private static final Map<String, String> shortcuts = new HashMap<>();

    static {
        shortcuts.put("weber", "WeberGMBH");
        shortcuts.put("l", "lol");
        shortcuts.put("x", "xD");
        shortcuts.put("h", "Hallo");
        shortcuts.put(")", ":)");
        shortcuts.put(":", ":D");
    }

    public OtherShortcuts() {
        super(
                "Andere Shortcuts",
                "Andere Shortcuts wie zum Beispiel x -> xD",
                "otherShortcuts",
                "otherShortcuts"
        );
    }

    @Override
    protected boolean isShortcut(ChatMessage msg) {
        return shortcuts.containsKey(msg.getText());
    }

    @Override
    protected void executeShortcut(ChatMessage msg) {
        Util.sendMsgToChat(shortcuts.get(msg.getText()));
    }
}
