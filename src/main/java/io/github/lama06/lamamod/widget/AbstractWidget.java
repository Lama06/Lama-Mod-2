package io.github.lama06.lamamod.widget;

import io.github.lama06.lamamod.feature.AbstractFeature;
import io.github.lama06.lamamod.events.HudRenderCallback;
import io.github.lama06.lamamod.options.BooleanOption;
import io.github.lama06.lamamod.options.NumberOption;
import net.minecraft.client.util.math.MatrixStack;

public abstract class AbstractWidget extends AbstractFeature implements HudRenderCallback {
    protected final BooleanOption shown = new BooleanOption(
            "Widget anzeigen",
            "Gibt an, ob das Widget angezeigt werden soll",
            "show",
            "",
            false
    );

    protected final NumberOption x = new NumberOption(
            "X-Koordinate",
            "Gibt die X-Koordinate des Widgets an",
            "x",
            "x",
            0
    );

    protected final NumberOption y = new NumberOption(
            "Y-Koordinate",
            "Gibt die Y-Koordinate des Widgets an",
            "y",
            "y",
            0
    );

    public AbstractWidget(String name, String description, String serializedName, String chatName) {
        super(name, description, serializedName, chatName);

        HudRenderCallback.EVENT.register(this);

        options.addOption(shown);
        options.addOption(x);
        options.addOption(y);
    }

    public void onHudRender(MatrixStack matrices) {
        if(shown.getValue()) {
            renderWidget(matrices);
        }
    }

    public abstract void renderWidget(MatrixStack matrices);
}
