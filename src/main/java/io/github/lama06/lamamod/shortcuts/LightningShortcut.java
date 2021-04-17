package io.github.lama06.lamamod.shortcuts;

import io.github.lama06.lamamod.shortcut.AbstractShortcut;
import io.github.lama06.lamamod.util.ChatMessage;
import io.github.lama06.lamamod.util.Util;

public final class LightningShortcut extends AbstractShortcut {
    public LightningShortcut() {
        super(
                "Blitz Shortcut",
                "Lässt einen Blitz bei jemandem einschlagen",
                "lightningShortcut",
                "lightningShortcut"
        );
    }

    @Override
    protected boolean isShortcut(ChatMessage msg) {
        return msg.getText().startsWith("blitz");
    }

    @Override
    protected void executeShortcut(ChatMessage msg) {
        String playerName;
        if (msg.getArgs().length == 0 || msg.getArgs().length > 1) {
            playerName = client.player.getName().asString();
            Util.sendMsgToPlayer("<Zeus> Ich bin sauer. Du hast mir nicht gesagt, wo der Blitz einschlagen soll!");
        } else {
            playerName = msg.getArgs()[0];
            Util.sendMsgToPlayer("<Zeus> Ich lasse jetzt einen Blitz bei " + playerName + " einschlagen.");
        }

        String command = String.format("/execute as %s at %s run summon minecraft:lightning_bolt ~ ~ ~", playerName, playerName);
        Util.sendMsgToChat(command);
    }
}
