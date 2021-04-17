package io.github.lama06.lamamod.widgets;

import io.github.lama06.lamamod.options.BooleanOption;
import io.github.lama06.lamamod.options.NumberOption;
import io.github.lama06.lamamod.widget.AbstractTextWidget;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;

public final class TargetedBlockWidget extends AbstractTextWidget {
    private final BooleanOption fluids = new BooleanOption(
            "Flüssigkeiten einbeziehen",
            "Gibt an, ob Flüssigkeiten berücksichtigt werden sollen",
            "fluid",
            "fluid",
            false
    );

    private final NumberOption distance = new NumberOption(
            "Maximale Entfernung",
            "Gibt an, wie weit ein Block maximal entfernt sein darf",
            "distance",
            "distance",
            20
    );

    public TargetedBlockWidget() {
        super(
                "Block Widget",
                "Zeigt den aktuell angeguckten Block an",
                "blockWidget",
                "blockWidget",
                "Block"
        );

        options.addOption(fluids);
        options.addOption(distance);

        shown.setDefaultValue(true);
        x.setDefaultValue(10);
        y.setDefaultValue(80);
    }

    @Override
    public String getText() {
        BlockHitResult result = (BlockHitResult) client.player.raycast((double) distance.getValue(), 0, fluids.getValue());

        if(result.getType() != HitResult.Type.BLOCK) {
            return "";
        } else {
            BlockPos position = result.getBlockPos();
            return String.format("%s %s %s", position.getX(), position.getY(), position.getZ());
        }
    }
}
