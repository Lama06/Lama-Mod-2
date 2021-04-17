package io.github.lama06.lamamod.shortcut;

import io.github.lama06.lamamod.feature.AbstractFeatureList;
import io.github.lama06.lamamod.shortcuts.*;

public final class ShortcutList extends AbstractFeatureList {
    public final TimeShortcut timeShortcut = new TimeShortcut();
    public final WeatherShortcut weatherShortcut = new WeatherShortcut();
    public final CoordinatesShortcut coordinatesShortcut = new CoordinatesShortcut();
    public final CrashShortcut crashShortcut = new CrashShortcut();
    public final LightningShortcut lightningShortcut = new LightningShortcut();
    public final OtherShortcuts otherShortcuts = new OtherShortcuts();
    public final TeleportShortcut teleportShortcut = new TeleportShortcut();
}
