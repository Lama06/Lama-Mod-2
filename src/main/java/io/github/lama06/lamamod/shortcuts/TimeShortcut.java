package io.github.lama06.lamamod.shortcuts;

import com.mojang.authlib.GameProfile;
import io.github.lama06.lamamod.shortcut.AbstractShortcut;
import io.github.lama06.lamamod.util.ChatMessage;
import io.github.lama06.lamamod.util.Util;
import net.minecraft.client.MinecraftClient;

import java.util.UUID;

public final class TimeShortcut extends AbstractShortcut {
    public TimeShortcut() {
        super(
                "Zeit Shortcuts",
                "Ändert die Zeit (zum Beispiel mit d)",
                "timeShortcut",
                "timeShortcut"
        );
    }

    private boolean isDayShortcut(ChatMessage msg) {
        String text = msg.getText();
        return text.equals("d") || text.equals("tsd") || text.equals("day");
    }

    private boolean isNightShortcut(ChatMessage msg) {
        String text = msg.getText();
        return text.equals("n") || text.equals("tsn") || text.equals("night");
    }

    @Override
    protected boolean isShortcut(ChatMessage msg) {
        return isDayShortcut(msg) || isNightShortcut(msg);
    }

    @Override
    protected void executeShortcut(ChatMessage msg) {
        MinecraftClient.getInstance().getSkinProvider().loadSkin(new GameProfile(UUID.fromString("069a79f4-44e9-4726-a5be-fca90e38aaf5"), "Notch"), (type, identifier, minecraftProfileTexture) -> {
            System.out.println(type);
            System.out.println(identifier);
        }, true);

        if(isDayShortcut(msg)) {
            Util.sendMsgToChat("/time set day");
        } else {
            Util.sendMsgToChat("/time set night");
        }
    }
}
