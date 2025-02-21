package com.github.sejoslaw.multiversecraft;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MultiverseCraft implements ModInitializer {
    public static final String MOD_ID = "multiversecraft";
    public static final String MOD_TITLE = "MultiverseCraft";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public void onInitialize() {
        LOGGER.info("Initializing MultiverseCraft...");

        LOGGER.info("Initializing MultiverseCraftItems...");
        MultiverseCraftItems.initialize();
        LOGGER.info("MultiverseCraftItems initialized.");
    }
}
