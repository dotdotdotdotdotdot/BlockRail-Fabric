package net.olin.blockrail;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.olin.blockrail.screen.exportscreen.ExportBlockScreen;
import net.olin.blockrail.screen.ModScreenHandlers;
import net.olin.blockrail.screen.tradecontrollerscreen.TradeControllerBlockScreen;

public class BlockRailClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

		HandledScreens.register(ModScreenHandlers.EXPORT_BLOCK_SCREEN_HANDLER, ExportBlockScreen::new);
		HandledScreens.register(ModScreenHandlers.TRADE_CONTROLLER_BLOCK_SCREEN_HANDLER, TradeControllerBlockScreen::new);
    }
}
