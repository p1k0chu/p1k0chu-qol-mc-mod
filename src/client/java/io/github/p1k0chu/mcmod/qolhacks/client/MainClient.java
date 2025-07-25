package io.github.p1k0chu.mcmod.qolhacks.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import org.lwjgl.glfw.GLFW;

import static io.github.p1k0chu.mcmod.qolhacks.constants.TranslationsConstants.*;

public final class MainClient implements ClientModInitializer {
    public static final KeyMapping elytraSpamToggle = new KeyMapping(ELYTRA_SPAM_KEY, GLFW.GLFW_KEY_V, KEY_CATEGORY_KEY);

    private static boolean elytraSpamEnabled = false;

    @Override
    public void onInitializeClient() {
        KeyBindingHelper.registerKeyBinding(elytraSpamToggle);

        ClientTickEvents.END_CLIENT_TICK.register((minecraft) -> {
            while (elytraSpamToggle.consumeClick()) {
                elytraSpamEnabled = !elytraSpamEnabled;

                final Component component;
                if (elytraSpamEnabled) {
                    component = ELYTRA_SPAM_ENABLED;
                } else {
                    component = ELYTRA_SPAM_DISABLED;
                }
                if (minecraft.player != null) {
                    minecraft.player.displayClientMessage(component, false);
                    minecraft.player.playSound(SoundEvents.AMETHYST_BLOCK_RESONATE);
                }
            }
        });
    }

    public static boolean isElytraSpamEnabled() {
        return elytraSpamEnabled;
    }
}
