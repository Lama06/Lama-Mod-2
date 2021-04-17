package io.github.lama06.lamamod.shortcuts;

import io.github.lama06.lamamod.shortcut.AbstractShortcut;
import io.github.lama06.lamamod.util.ChatMessage;
import io.github.lama06.lamamod.util.Util;
import net.minecraft.client.network.PlayerListEntry;

import java.util.Locale;

public final class TeleportShortcut extends AbstractShortcut {
    public TeleportShortcut() {
        super(
                "Teleport Shortcut",
                "Teleportiert einen zu einem Spieler, wenn man dessen Namen eingibt",
                "teleportShortcut",
                "teleportShortcut"
        );
    }

    @Override
    protected boolean isShortcut(ChatMessage msg) {
        for(PlayerListEntry player : client.getNetworkHandler().getPlayerList()) {
            if(player.getProfile().getName().toLowerCase(Locale.ROOT).equals(msg.getText())) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void executeShortcut(ChatMessage msg) {
        Util.sendMsgToChat(String.format("/tp %s", msg.getPlainText()));
    }
}
