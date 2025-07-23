package io.github.p1k0chu.mcmod.qolhacks;

import net.minecraft.network.chat.Component;

public enum Language {
    KEY_CATEGORY("p1k0chu_qol_mod.key_category"),
    ELYTRA_SPAM("p1k0chu_qol_mod.key.elytra_spam");

    private final String key;

    Language(String key) {
        this.key = key;
    }

    public Component getText() {
        return Component.translatable(key);
    }

    public Component getText(Object... obj) {
        return Component.translatable(key, obj);
    }

    public String getKey() {
        return key;
    }
}
