package org.example;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Main implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("mod_id");

    @Override
    public void onInitialize() {
        LOGGER.info("Example mod initialized!");
    }
}