package io.github.lama06.lamamod;

import io.github.lama06.lamamod.options.OptionsFile;
import io.github.lama06.lamamod.shortcut.ShortcutList;
import io.github.lama06.lamamod.version.GithubRelease;
import io.github.lama06.lamamod.version.ModVersion;
import io.github.lama06.lamamod.widget.WidgetList;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.network.ServerInfo;

public class LamaMod implements ClientModInitializer {
    public static final String prefix = "[LamaMod] ";
    public static final ModVersion version = new ModVersion(2, 0, 1);
    public static final WidgetList widgets = new WidgetList();
    public static final ShortcutList shortcuts = new ShortcutList();
    public static final OptionsFile options = new OptionsFile();
    public static final ServerInfo lamaServerAddress = new ServerInfo("Lama Server", "lamaserver.ddns.net", false);

    @Override
    public void onInitializeClient() {
        System.out.printf("Lama Mod %s geladen%n", version.toVersionTag());

        if(GithubRelease.isNewerModVersionAvailable()) {
            GithubRelease newestRelease = GithubRelease.fetchNewestRelease();
            System.out.printf("Es ist eine neuere Version der LamaMod verfügbar: %s%n", newestRelease.versionTag);
        } else {
            System.out.println("Du benutzt die neuste Version der LamaMod");
        }

        options.load();
        options.save();
    }
}
