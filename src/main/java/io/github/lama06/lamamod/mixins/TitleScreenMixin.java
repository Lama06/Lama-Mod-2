package io.github.lama06.lamamod.mixins;

import io.github.lama06.lamamod.gui.LamaServerButtonFactory;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen {
    protected TitleScreenMixin(Text title) {
        super(title);
    }

    @Inject(at = @At("HEAD"), method = "initWidgetsNormal")
    public void addLamaServerButton(int y, int spacingY, CallbackInfo ci) {
        addDrawableChild(new LamaServerButtonFactory(this).create());
    }
}
