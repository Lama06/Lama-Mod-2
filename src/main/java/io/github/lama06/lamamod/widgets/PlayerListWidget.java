package io.github.lama06.lamamod.widgets;

import io.github.lama06.lamamod.options.NumberOption;
import io.github.lama06.lamamod.widget.AbstractTextWidget;
import net.minecraft.client.network.PlayerListEntry;

import java.util.Collection;

public final class PlayerListWidget extends AbstractTextWidget {
    private final NumberOption maxPlayers = new NumberOption(
            "Maximale Spieler",
            "Anzahl der maximalen Spieler",
            "maxPlayers",
            "maxPlayers",
            5
    );

    public PlayerListWidget() {
        super(
                "Spieler Widget",
                "Eine Liste aller Spieler auf dem Server",
                "playersWidget",
                "playersWidget",
                "Spieler"
        );

        options.addOption(maxPlayers);

        shown.setDefaultValue(true);
        x.setDefaultValue(10);
        y.setDefaultValue(60);
    }

    @Override
    public String getText() {
        StringBuilder text = new StringBuilder();

        Collection<PlayerListEntry> players = client.getNetworkHandler().getPlayerList();
        int totalAmountOfPlayers = players.size();

        text.append("(").append(totalAmountOfPlayers).append(") ");

        int playersAdded = 0;
        for(PlayerListEntry player : players) {
            if(playersAdded < maxPlayers.getValue() || maxPlayers.getValue() <= 0) {
                text.append(player.getProfile().getName()).append(" ");
                playersAdded++;
            } else {
                int leftPlayers = totalAmountOfPlayers - playersAdded;
                text.append("(+").append(leftPlayers).append(" ").append(leftPlayers == 1 ? "weiterer" : "weitere").append(")");
                break;
            }
        }

        return text.toString();
    }
}
