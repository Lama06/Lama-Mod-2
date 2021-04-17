package io.github.lama06.lamamod.widget;

import io.github.lama06.lamamod.feature.AbstractFeatureList;
import io.github.lama06.lamamod.widgets.*;

public final class WidgetList extends AbstractFeatureList {
    public final FpsWidget fpsWidget = new FpsWidget();
    public final TimeWidget timeWidget = new TimeWidget();
    public final VersionWidget versionWidget = new VersionWidget();
    public final CoordinatesWidget coordinatesWidget = new CoordinatesWidget();
    public final KeystrokesWidget keystrokesWidget = new KeystrokesWidget();
    public final PlayerListWidget playerListWidget = new PlayerListWidget();
    public final BiomeWidget biomeWidget = new BiomeWidget();
    public final TargetedBlockWidget targetedBlockWidget = new TargetedBlockWidget();
}
