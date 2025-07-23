package io.github.p1k0chu.mcmod.qolhacks.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

import static io.github.p1k0chu.mcmod.qolhacks.Language.ELYTRA_SPAM;
import static io.github.p1k0chu.mcmod.qolhacks.Language.KEY_CATEGORY;

public final class MainClient implements ClientModInitializer {
    public static final KeyMapping elytraSpamToggle = new KeyMapping(ELYTRA_SPAM.getKey(), GLFW.GLFW_KEY_V, KEY_CATEGORY.getKey());

    private static boolean elytraSpamEnabled = false;

    @Override
    public void onInitializeClient() {
        KeyBindingHelper.registerKeyBinding(elytraSpamToggle);

        ClientTickEvents.END_CLIENT_TICK.register((minecraft) -> {
            while (elytraSpamToggle.consumeClick()) {
                elytraSpamEnabled = !elytraSpamEnabled;
            }
        });
    }

    public static boolean isElytraSpamEnabled() {
        return elytraSpamEnabled;
    }
}
