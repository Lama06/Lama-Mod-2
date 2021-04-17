package io.github.lama06.lamamod.widgets;

import io.github.lama06.lamamod.options.BooleanOption;
import io.github.lama06.lamamod.util.Util;
import io.github.lama06.lamamod.widget.AbstractTextWidget;

public final class CoordinatesWidget extends AbstractTextWidget {
    private final BooleanOption nether = new BooleanOption(
            "Nether Koordinaten",
            "Gibt an, ob die zugehörigen Nether Koordinaten angezeigt werden sollen",
            "showNetherCoordinates",
            "nether",
            true
    );

    public CoordinatesWidget() {
        super(
                "Koordinaten Widget",
                "Zeigt die aktuellen Koordinaten an",
                "coordinatesWidget",
                "coordsWidget",
                "Koordinaten"
        );

        options.addOption(nether);

        shown.setDefaultValue(true);
        x.setDefaultValue(10);
        y.setDefaultValue(40);
    }

    @Override
    public String getText() {
        return Util.getCoordinatesString(nether.getValue());
    }
}
