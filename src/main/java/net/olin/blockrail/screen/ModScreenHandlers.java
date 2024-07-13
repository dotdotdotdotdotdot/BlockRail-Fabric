package net.olin.blockrail.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.olin.blockrail.BlockRail;
import net.olin.blockrail.screen.tradecontrollerscreen.Trade0_screen_handler;
import net.olin.blockrail.screen.tradecontrollerscreen.Trade1_screen_handler;

public class ModScreenHandlers {

	public static final ScreenHandlerType<Trade0_screen_handler> TRADE0_SCREEN_HANDLER =
			Registry.register(Registries.SCREEN_HANDLER, new Identifier(BlockRail.MOD_ID, "trade0_controller_block"),
					new ExtendedScreenHandlerType<>(Trade0_screen_handler::new));

	public static final ScreenHandlerType<Trade1_screen_handler> TRADE1_SCREEN_HANDLER =
			Registry.register(Registries.SCREEN_HANDLER, new Identifier(BlockRail.MOD_ID, "trade1_controller_block"),
					new ExtendedScreenHandlerType<>(Trade1_screen_handler::new));

	public static void registerScreenHandler() {
		BlockRail.LOGGER.info("Loading screens for " + BlockRail.MOD_ID);
	}
}
