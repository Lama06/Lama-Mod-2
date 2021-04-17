package io.github.lama06.lamamod.shortcuts;

import io.github.lama06.lamamod.shortcut.AbstractShortcut;
import io.github.lama06.lamamod.util.ChatMessage;
import io.github.lama06.lamamod.util.Util;

public final class CoordinatesShortcut extends AbstractShortcut {
    public CoordinatesShortcut() {
        super(
                "Koordinaten Shortcut",
                "Schreibt die aktuelle Koordinaten in den Chat",
                "coordinatesShortcut",
                "coordinatesShortcut"
        );
    }

    @Override
    protected boolean isShortcut(ChatMessage msg) {
        String text = msg.getText();
        return text.equals("c") || text.equals("coordinates") || text.equals("koordinaten");
    }

    @Override
    protected void executeShortcut(ChatMessage msg) {
        Util.sendMsgToChat(Util.getCoordinatesString(false));
    }
}
