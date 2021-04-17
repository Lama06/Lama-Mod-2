package io.github.lama06.lamamod.mixins;

import io.github.lama06.lamamod.events.EventResult;
import io.github.lama06.lamamod.events.MessageSentCallback;
import io.github.lama06.lamamod.util.ChatMessage;
import io.github.lama06.lamamod.util.Util;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChatScreen.class)
public abstract class ChatScreenMixin extends Screen {
    @Shadow protected TextFieldWidget chatField;

    protected ChatScreenMixin(Text title) {
        super(title);
    }

    @Inject(at = @At("HEAD"), method = "keyPressed", cancellable = true)
    public void keyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        // Wenn Enter gedrückt wird und die Nachricht abgesendet wird
        if(keyCode == 257) {
            ChatMessage msg = new ChatMessage(chatField.getText());
            EventResult result = MessageSentCallback.EVENT.invoker().onChatMessage(msg);

            if (result == EventResult.PASS && msg.getPlainText().length() != 0) {
                Util.sendMsgToChat(msg.getPlainText());
            }

            Util.addMsgToChatHistory(msg.getPlainText());
            client.openScreen(null);
            cir.setReturnValue(true);
        }
    }
}
