package io.github.p1k0chu.mcmod.qolhacks.constants;

import net.minecraft.network.chat.Component;

import static net.minecraft.network.chat.Component.translatable;

public final class TranslationsConstants {
    private TranslationsConstants() {
    }

    public static final String KEY_CATEGORY_KEY = "p1k0chu_qol_mod.key_category";

    public static final String ELYTRA_SPAM_KEY = "p1k0chu_qol_mod.key.elytra_spam";
    public static final String ELYTRA_SPAM_ENABLED_KEY = "p1k0chu_qol_mod.function.elytra_spam.enabled";
    public static final String ELYTRA_SPAM_DISABLED_KEY = "p1k0chu_qol_mod.function.elytra_spam.disabled";

    public static final Component ELYTRA_SPAM_ENABLED = translatable(ELYTRA_SPAM_ENABLED_KEY);
    public static final Component ELYTRA_SPAM_DISABLED = translatable(ELYTRA_SPAM_DISABLED_KEY);
}
