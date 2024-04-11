package net.olin.blockrail.screen.tradecontrollerscreen;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.IconWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.olin.blockrail.BlockRail;
import net.olin.blockrail.trades.Trade;
import net.olin.blockrail.trades.Trades;

import static com.ibm.icu.text.PluralRules.Operand.i;

public class TradeControllerBlockScreen extends HandledScreen<TradeControllerBlockScreenHandler> {
	private static final Identifier TEXTURE = new Identifier(BlockRail.MOD_ID, "textures/gui/trade_controller_block_gui.png");
	public TradeControllerBlockScreen(TradeControllerBlockScreenHandler handler, PlayerInventory inventory, Text title) {
		super(handler, inventory, title);
    }
	@Override
	protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
		int x = (width - 378) / 2;
		int y = (height - backgroundHeight) / 2;

		context.drawTexture(TEXTURE, x, y, 0, 0, 276, 166, 512, 256);
		renderCounterBar(context, x, y);
	}
	@Override
	protected void drawForeground(DrawContext context, int mouseX, int mouseY) {
		int x = (width - 378) / 2;
		int y = (height - backgroundHeight) / 2;

		super.drawForeground(context, mouseX, mouseY);
		tradeButtons(x, y);
		renderCounter(context, x, y);
	}

	protected void tradeButtons(int imx, int imy) {
		int x = ((width - 378) / 2) + 5;
		int y = ((height - backgroundHeight) / 2) + 18;

		for (int i = 0; i < Trades.TRADES.size(); i++) {
			if (handler.getSelectedButtonIndex() == i) {
				drawButton(x, y+i*28, 28, Trades.TRADES.get(i), i);
			} else {
				drawButton(x, y+i*28, 0, Trades.TRADES.get(i), i);
			}
		}
	}

	private void renderCounter(DrawContext context, int imx, int imy) {
		int x = (width - 378) / 2;
		int y = (height - backgroundHeight) / 2;

		if (handler.isTrading()) {
			addDrawable(new TextWidget(x+50, y+7, 16,16, Text.literal(String.valueOf(handler.getCount())), textRenderer));
		}
	}

	private void renderCounterBar(DrawContext context, int imx, int imy) {
		int x = (width - 378) / 2;
		int y = (height - backgroundHeight) / 2;

		if (handler.isTrading()) {
			context.drawTexture(TEXTURE, x + 171, y + 15, 277, handler.getCounterProgress() - 58, 30, 59, 512, 256);
			context.drawTexture(TEXTURE, x + 171, y + 73, 277, 58, 30, 1, 512, 256);
		}
	}
	protected void drawButton(int x, int y, int v, Trade trade, int i) {
		addDrawableChild(new TexturedButtonWidget(x, y, 82, 28, 0, 166 + v, 0, TEXTURE, 512, 256, button ->
                handler.setSelectedButton(i)));
		addDrawable(new IconWidget(x + 6, y + 6, 16, 16, trade.getTexture()));
		addDrawable(new TextWidget(x + 29, y + 7, 16, 16, Text.literal(trade.getStringCost()), textRenderer).alignRight());
	}
	@Override
	public void render(DrawContext context, int mouseX, int mouseY, float delta) {
		renderBackground(context);
		super.render(context, mouseX, mouseY, delta);
		drawMouseoverTooltip(context, mouseX, mouseY);
	}
}
