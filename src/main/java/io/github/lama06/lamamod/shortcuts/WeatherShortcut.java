package io.github.lama06.lamamod.shortcuts;

import io.github.lama06.lamamod.shortcut.AbstractShortcut;
import io.github.lama06.lamamod.util.ChatMessage;
import io.github.lama06.lamamod.util.Util;

public final class WeatherShortcut extends AbstractShortcut {
    public WeatherShortcut() {
        super(
                "Wetter Shortcut",
                "Ändert das Wetter",
                "weatherShortcut",
                "weatherShortcut"
        );
    }

    private boolean isRainShortcut(ChatMessage msg) {
        String text = msg.getText();
        return text.equals("wr") || text.equals("rain");
    }

    private boolean isClearShortcut(ChatMessage msg) {
        return msg.getText().equals("wc");
    }

    @Override
    protected boolean isShortcut(ChatMessage msg) {
        return isClearShortcut(msg) || isRainShortcut(msg);
    }

    @Override
    protected void executeShortcut(ChatMessage msg) {
        if(isClearShortcut(msg)) {
            Util.sendMsgToChat("/weather clear");
        } else {
            Util.sendMsgToChat("/weather rain");
        }
    }
}
