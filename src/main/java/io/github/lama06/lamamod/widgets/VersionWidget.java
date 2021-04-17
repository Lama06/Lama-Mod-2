package io.github.lama06.lamamod.widgets;

import io.github.lama06.lamamod.LamaMod;
import io.github.lama06.lamamod.options.BooleanOption;
import io.github.lama06.lamamod.version.GithubRelease;
import io.github.lama06.lamamod.widget.AbstractTextWidget;

public final class VersionWidget extends AbstractTextWidget {
    private final BooleanOption checkForUpdates = new BooleanOption(
            "Auf Updates überprüfen",
            "Gibt an, ob auf Updates überprüft werden soll",
            "checkForUpdates",
            "update",
            true
    );

    public VersionWidget() {
        super(
                "Versions Widget",
                "Zeigt die Version der Mod an",
                "versionWidget",
                "versionWidget",
                "Version"
        );

        options.addOption(checkForUpdates);

        shown.setDefaultValue(true);
        x.setDefaultValue(10);
        y.setDefaultValue(30);
    }

    @Override
    public String getText() {
        if(checkForUpdates.getValue() && GithubRelease.isNewerModVersionAvailable()) {
            GithubRelease newestRelease = GithubRelease.fetchNewestRelease();
            return String.format("%s (Update verfügbar: %s)", LamaMod.version.toVersionTag(), newestRelease.versionTag);
        } else {
            return LamaMod.version.toVersionTag();
        }
    }
}
