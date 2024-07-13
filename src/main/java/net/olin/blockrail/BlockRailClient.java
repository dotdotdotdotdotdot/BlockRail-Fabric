package net.olin.blockrail;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.olin.blockrail.screen.ModScreenHandlers;
import net.olin.blockrail.screen.tradecontrollerscreen.Trade0_screen;
import net.olin.blockrail.screen.tradecontrollerscreen.Trade1_screen;

public class BlockRailClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
		HandledScreens.register(ModScreenHandlers.TRADE0_SCREEN_HANDLER, Trade0_screen::new);
        HandledScreens.register(ModScreenHandlers.TRADE1_SCREEN_HANDLER, Trade1_screen::new);
    }
}
