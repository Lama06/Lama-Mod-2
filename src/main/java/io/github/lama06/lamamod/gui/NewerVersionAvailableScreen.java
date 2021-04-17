package io.github.lama06.lamamod.gui;

import io.github.lama06.lamamod.LamaMod;
import io.github.lama06.lamamod.util.Color;
import io.github.lama06.lamamod.util.Util;
import io.github.lama06.lamamod.version.GithubRelease;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;

public final class NewerVersionAvailableScreen extends Screen {
    private final Runnable onCancel;
    private final MinecraftClient client = MinecraftClient.getInstance();
    private  final GithubRelease newestRelease = GithubRelease.fetchNewestRelease();

    public NewerVersionAvailableScreen(Runnable onCancel) {
        super(new LiteralText("Neue Version der Lama Mod verfügbar"));
        this.onCancel = onCancel;
    }

    protected void init() {
        super.init();
        addButton(new ButtonWidget(width / 2 - 155, height / 6 + 96, 150, 20, new TranslatableText("Abbrechen"), (button) -> {
            onCancel.run();
        }));
        addButton(new ButtonWidget(width / 2 - 155 + 160, height / 6 + 96, 150, 20, new LiteralText("Herunterladen"), (button) -> {
            Util.openURL(newestRelease.htmlUrl);
        }));
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        drawCenteredText(matrices, textRenderer, title, width / 2, 70, Color.white.toInt());
        drawCenteredText(matrices, textRenderer,
                new LiteralText("Soll diese Version der Mod heruntergeladen werden: " + newestRelease.versionTag + " (aktuelle Version: " + LamaMod.version.toVersionTag() + ")?"),
                width / 2, 90, Color.white.toInt());
        super.render(matrices, mouseX, mouseY, delta);
    }

    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            onCancel.run();
            return true;
        } else {
            return super.keyPressed(keyCode, scanCode, modifiers);
        }
    }
}
