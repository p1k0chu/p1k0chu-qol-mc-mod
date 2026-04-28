package io.github.p1k0chu.mcmod.qolhacks.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.KeyMapping.Category;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import com.mojang.blaze3d.platform.InputConstants;

import static io.github.p1k0chu.mcmod.qolhacks.Main.MOD_ID;
import static io.github.p1k0chu.mcmod.qolhacks.constants.TranslationsConstants.*;

public final class MainClient implements ClientModInitializer {
    private static final Category KEY_CATEGORY = Category.register(Identifier.fromNamespaceAndPath(MOD_ID, "misc"));
    private static boolean elytraSpamEnabled = false;

    @Override
    public void onInitializeClient() {
        KeyMapping elytraSpamToggle = new KeyMapping(ELYTRA_SPAM_KEY, InputConstants.UNKNOWN.getValue(), KEY_CATEGORY);
        KeyMappingHelper.registerKeyMapping(elytraSpamToggle);

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
                    //? >=26.1 {
                    minecraft.player.sendOverlayMessage(component);
                    //? } else
                    //minecraft.player.displayClientMessage(component, true);
                    minecraft.player.playSound(SoundEvents.AMETHYST_BLOCK_RESONATE);
                }
            }
        });
    }

    public static boolean isElytraSpamEnabled() {
        return elytraSpamEnabled;
    }
}
