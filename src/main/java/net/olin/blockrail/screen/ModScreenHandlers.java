package net.olin.blockrail.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.olin.blockrail.BlockRail;
import net.olin.blockrail.screen.exportscreen.ExportBlockScreenHandler;
import net.olin.blockrail.screen.marketscreen.MarketBlockGuiHandler;
import net.olin.blockrail.screen.tradecontrollerscreen.TradeControllerBlockScreenHandler;

public class ModScreenHandlers {
	public static final ScreenHandlerType<ExportBlockScreenHandler> EXPORT_BLOCK_SCREEN_HANDLER =
			Registry.register(Registries.SCREEN_HANDLER, new Identifier(BlockRail.MOD_ID, "export_block"),
					new ExtendedScreenHandlerType<>(ExportBlockScreenHandler::new));

	public static final ScreenHandlerType<TradeControllerBlockScreenHandler> TRADE_CONTROLLER_BLOCK_SCREEN_HANDLER =
			Registry.register(Registries.SCREEN_HANDLER, new Identifier(BlockRail.MOD_ID, "trade_controller_block"),
					new ExtendedScreenHandlerType<>(TradeControllerBlockScreenHandler::new));
	public static final ScreenHandlerType<MarketBlockGuiHandler> MARKET_BLOCK_GUI_HANDLER =
			Registry.register(Registries.SCREEN_HANDLER, new Identifier(BlockRail.MOD_ID, "market_block"),
			new ScreenHandlerType<>((syncId, inventory) -> new MarketBlockGuiHandler(syncId, inventory, ScreenHandlerContext.EMPTY),
					FeatureFlags.VANILLA_FEATURES));


	public static void registerScreenHandler() {
		BlockRail.LOGGER.info("Loading screens for " + BlockRail.MOD_ID);
	}
}
