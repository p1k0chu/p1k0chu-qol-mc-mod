package org.example.client;

import net.fabricmc.api.ClientModInitializer;
import org.example.Main;

public final class MainClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Main.LOGGER.info("Example mod initialized on client!");
    }
}
