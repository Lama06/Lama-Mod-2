package io.github.lama06.lamamod.widgets;

import io.github.lama06.lamamod.widget.AbstractTextWidget;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

public final class BiomeWidget extends AbstractTextWidget {
    public BiomeWidget() {
        super(
                "Biom Widget",
                "Zeigt das aktuelle Biom an",
                "biomeWidget",
                "biomeWidget",
                "Biom"
        );

        shown.setDefaultValue(true);
        x.setDefaultValue(10);
        y.setDefaultValue(70);
    }

    @Override
    public String getText() {
        Biome biome = client.world.getBiome(client.player.getBlockPos());
        return client.world.getRegistryManager().get(Registry.BIOME_KEY).getId(biome).toString();
    }
}
