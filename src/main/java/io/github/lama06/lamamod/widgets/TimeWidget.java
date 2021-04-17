package io.github.lama06.lamamod.widgets;

import io.github.lama06.lamamod.options.BooleanOption;
import io.github.lama06.lamamod.widget.AbstractTextWidget;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class TimeWidget extends AbstractTextWidget {
    private final BooleanOption date = new BooleanOption(
            "Datum anzeigen",
            "Gibt an, ob das Datum angezeigt werden soll",
            "date",
            "date",
            true
    );

    private final BooleanOption seconds = new BooleanOption(
            "Sekunden anzeigen",
            "Gibt an, ob die Sekunden angezeigt werden sollen",
            "seconds",
            "seconds",
            true
    );

    public TimeWidget() {
        super(
                "Zeit Widget",
                "Zeigt die aktuelle Zeit an",
                "timeWidget",
                "timeWidget",
                "Zeit"
        );

        options.addOption(date);
        options.addOption(seconds);

        shown.setDefaultValue(true);
        x.setDefaultValue(10);
        y.setDefaultValue(20);
    }

    @Override
    public String getText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                (date.getValue() ? "dd.MM " : "") +
                "HH:mm" +
                (seconds.getValue() ? ":ss" : "")
        );
        LocalDateTime time = LocalDateTime.now();
        return formatter.format(time);
    }
}
