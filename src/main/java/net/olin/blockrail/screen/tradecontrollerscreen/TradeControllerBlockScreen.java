package net.olin.blockrail.screen.tradecontrollerscreen;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.olin.blockrail.BlockRail;

public class TradeControllerBlockScreen extends HandledScreen<TradeControllerBlockScreenHandler> {
	private static final Identifier TEXTURE = new Identifier(BlockRail.MOD_ID, "textures/gui/trade_controller_block_gui.png");

	public TradeControllerBlockScreen(TradeControllerBlockScreenHandler handler, PlayerInventory inventory, Text title) {
		super(handler, inventory, title);
	}



	@Override
	protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
		int initialX = width/2 - 378/2;
		int initialY = (height - backgroundHeight) / 2;
		int textureWidth = 512;
		int textureHeight = 256;

		context.drawTexture(TEXTURE, initialX, initialY, 0, 0, 276, 166, textureWidth, textureHeight);
	}

	private static final TexturedButtonWidget MENU_BUTTON = new TexturedButtonWidget(0,
			0,
			82,
			30,
			0,
			166,
			0,
			TEXTURE,
			512,
			256,
			button -> System.out.println("Hello"));

	@Override
	protected void drawForeground(DrawContext context, int mouseX, int mouseY) {
		super.drawForeground(context, mouseX, mouseY);
		addDrawableChild(MENU_BUTTON);
	}


	@Override
	public void render(DrawContext context, int mouseX, int mouseY, float delta) {
		renderBackground(context);
		super.render(context, mouseX, mouseY, delta);
		drawMouseoverTooltip(context, mouseX, mouseY);
	}
}
