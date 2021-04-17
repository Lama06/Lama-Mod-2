package io.github.lama06.lamamod.widgets;

import io.github.lama06.lamamod.options.BooleanOption;
import io.github.lama06.lamamod.util.Util;
import io.github.lama06.lamamod.widget.AbstractTextWidget;
import net.minecraft.client.util.InputUtil;

public final class KeystrokesWidget extends AbstractTextWidget {
    private final BooleanOption wasd = new BooleanOption(
            "WASD Tasten einbeziehen",
            "Gibt an, ob die WASD-Tasten einbezogen werden sollen",
            "wasd",
            "wasd",
            true
    );

    private final BooleanOption other = new BooleanOption(
            "Andere Tasten einbeziehen",
            "Gibt an, ob andere Tasten, wie Ctrl oder Shift einbezogen werden sollen",
            "other",
            "other",
            true
    );

    public KeystrokesWidget() {
        super(
                "Keystrokes Widget",
                "Zeigt die aktuell gedrückten Tasten an",
                "keystrokesWidget",
                "keystrokesWidget",
                "Keystrokes"
        );

        options.addOption(wasd);
        options.addOption(other);

        shown.setDefaultValue(true);
        x.setDefaultValue(10);
        y.setDefaultValue(50);
    }

    @Override
    public String getText() {
        long handle = Util.getHandle();
        StringBuilder text = new StringBuilder();

        if(other.getValue()) {
            if(InputUtil.isKeyPressed(handle, 341))
                text.append("CTRL ");

            if(InputUtil.isKeyPressed(handle, 340))
                text.append("SHIFT ");
        }

        if(wasd.getValue()) {
            if(InputUtil.isKeyPressed(handle, 32))
                text.append("SPACE ");

            if(InputUtil.isKeyPressed(handle, 87))
                text.append("W");

            if(InputUtil.isKeyPressed(handle, 65))
                text.append("A");

            if(InputUtil.isKeyPressed(handle, 83))
                text.append("S");

            if(InputUtil.isKeyPressed(handle, 68))
                text.append("D");
        }

        return text.toString();
    }
}
