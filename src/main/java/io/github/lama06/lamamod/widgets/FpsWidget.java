package io.github.lama06.lamamod.widgets;

import io.github.lama06.lamamod.mixins.MinecraftClientAccessor;
import io.github.lama06.lamamod.widget.AbstractTextWidget;

public final class FpsWidget extends AbstractTextWidget {
    public FpsWidget() {
        super(
                "Fps Widget",
                "Ein Widget, das die aktuellen FPS anzeigt",
                "fpsWidget",
                "fpsWidget",
                "Fps"
        );

        shown.setDefaultValue(true);
        x.setDefaultValue(10);
        y.setDefaultValue(10);
    }

    @Override
    public String getText() {
        return Integer.toString(MinecraftClientAccessor.getCurrentFps());
    }
}
