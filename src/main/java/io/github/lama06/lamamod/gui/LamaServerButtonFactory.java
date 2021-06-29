package io.github.lama06.lamamod.gui;

import io.github.lama06.lamamod.LamaMod;
import io.github.lama06.lamamod.util.Util;
import io.github.lama06.lamamod.version.GithubRelease;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.TranslatableText;

public final class LamaServerButtonFactory {
    private final int x, y;
    private final MinecraftClient client = MinecraftClient.getInstance();
    private final Screen parent;

    public LamaServerButtonFactory(Screen parent, int x, int y) {
        this.x = x;
        this.y = y;
        this.parent = parent;
    }

    public LamaServerButtonFactory(Screen parent) {
        this(parent, 20, 20);
    }

    private void connectToServer() {
        Util.connectToServer(parent, LamaMod.lamaServerInfo, LamaMod.lamaServerAdress);
    }

    private void onClick(ButtonWidget widget) {
        if(GithubRelease.isNewerModVersionAvailable()) {
            client.openScreen(new NewerVersionAvailableScreen(this::connectToServer));
        } else {
            connectToServer();
        }
    }

    public ButtonWidget create() {
        return new ButtonWidget(x, y, 200, 20, new TranslatableText("Lama Server"), this::onClick);
    }
}
