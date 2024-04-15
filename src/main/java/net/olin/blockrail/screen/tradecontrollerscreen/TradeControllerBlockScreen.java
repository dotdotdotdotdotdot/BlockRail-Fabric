package net.olin.blockrail.screen.tradecontrollerscreen;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import net.olin.blockrail.BlockRail;
import net.olin.blockrail.trades.Trade;
import net.olin.blockrail.trades.Trades;

public class TradeControllerBlockScreen extends HandledScreen<TradeControllerBlockScreenHandler> {
	private static final Identifier TEXTURE = new Identifier(BlockRail.MOD_ID, "textures/gui/trade_controller_block_gui.png");
	public TradeControllerBlockScreen(TradeControllerBlockScreenHandler handler, PlayerInventory inventory, Text title) {
		super(handler, inventory, title);
		titleX = -92;
    }
	@Override
	protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
		int x = (width - 378) / 2;
		int y = (height - backgroundHeight) / 2;

		context.drawTexture(TEXTURE, x, y, 0, 0, 276, 166, 512, 256);
		renderCounterBar(context, x, y);
		renderCounter(context, x, y);
		renderSelectedTrade(context, x, y);
	}
	@Override
	protected void drawForeground(DrawContext context, int mouseX, int mouseY) {
		int x = (width - 378) / 2;
		int y = (height - backgroundHeight) / 2;

		super.drawForeground(context, mouseX, mouseY);
		renderTradeButtons(context, x, y);
	}

	protected void renderTradeButtons(DrawContext context, int imx, int imy) {
		int x = ((width - 378) / 2) + 5;
		int y = ((height - backgroundHeight) / 2) + 18;

		for (int i = 0; i < Trades.TRADES.size(); i++) {
			if (handler.getSelectedButtonIndex() == i) {
				renderButton(context, x, y+i*28, 28, Trades.TRADES.get(i), i);
			} else {
				renderButton(context, x, y+i*28, 0, Trades.TRADES.get(i), i);
			}
		}
	}
	private void renderSelectedTrade(DrawContext context, int imx, int imy) {
		int x = (width - 378) / 2;
		int y = (height - backgroundHeight) / 2;

		context.drawText(textRenderer, String.valueOf(handler.getSelectedButtonIndex()), x, y, Colors.WHITE, false);
	}
	protected void renderButton(DrawContext context, int x, int y, int v, Trade trade, int i) {
		addDrawableChild(new TexturedButtonWidget(x, y, 82, 28, 0, 166 + v, 0, TEXTURE, 512, 256, button ->
		{
			handler.setSelectedButtonIndex(i);
			System.out.println("SADASDA");
		}));

		context.drawTexture(trade.getTexturePath(), x + 6, y + 6, 0, 0, 16, 16, 16, 16);
		context.drawText(textRenderer, trade.inputAmountToString(), x - 85, y - 32, Colors.WHITE, false);
	}
	private void renderCounter(DrawContext context, int imx, int imy) {
		int x = (width - 378) / 2;
		int y = (height - backgroundHeight) / 2;

		if (handler.isTrading()) {
			context.drawText(textRenderer, String.valueOf(handler.getCount()), x + 50, y + 7, Colors.WHITE, false);
		}
	}
	private void renderCounterBar(DrawContext context, int imx, int imy) {
		int x = (width - 378) / 2;
		int y = (height - backgroundHeight) / 2;

		if (handler.isTrading()) {
			context.drawTexture(TEXTURE, x + 171, y + 15, 277, handler.getCounterProgress() - 58, 30, 59, 512, 256);
			context.drawTexture(TEXTURE, x + 171, y + 73, 277, 59, 30, 1, 512, 256);
		}
	}

	@Override
	public void render(DrawContext context, int mouseX, int mouseY, float delta) {
		renderBackground(context);
		super.render(context, mouseX, mouseY, delta);
		drawMouseoverTooltip(context, mouseX, mouseY);
	}
}
