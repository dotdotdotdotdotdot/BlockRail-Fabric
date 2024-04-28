package net.olin.blockrail.screen.marketscreen;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;

public class MarketBlockScreen extends CottonInventoryScreen<MarketBlockGuiHandler> {
    public MarketBlockScreen(MarketBlockGuiHandler gui, PlayerEntity player, Text title) {
        super(gui, player, title);
    }
}
