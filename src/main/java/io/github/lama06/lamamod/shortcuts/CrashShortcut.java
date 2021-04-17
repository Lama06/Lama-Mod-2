package io.github.lama06.lamamod.shortcuts;

import io.github.lama06.lamamod.shortcut.AbstractShortcut;
import io.github.lama06.lamamod.util.ChatMessage;
import io.github.lama06.lamamod.util.Util;

public final class CrashShortcut extends AbstractShortcut {
    public CrashShortcut() {
        super(
                "Crash Shortcut",
                "Crasht das Spiel",
                "crashShortcut",
                "crashShortcut"
        );
    }

    @Override
    protected boolean isShortcut(ChatMessage msg) {
        String text = msg.getText();
        return text.equals("tschüss") || text.equals("wehner") || text.equals("wehnergmbh") || text.equals("lamas sind doof");
    }

    @Override
    protected void executeShortcut(ChatMessage msg) {
        Util.crashGame("kaputt");
    }
}
