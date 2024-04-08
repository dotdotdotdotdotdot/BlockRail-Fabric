package net.olin.blockrail.screen.tradecontrollerscreen;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.IconWidget;
import net.minecraft.client.gui.widget.ScrollableWidget;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.olin.blockrail.BlockRail;
import net.olin.blockrail.trades.Trade;
import net.olin.blockrail.trades.Trades;

public class TradeControllerBlockScreen extends HandledScreen<TradeControllerBlockScreenHandler> {
	private int buttonV = 166;
	private int selected = -1;
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

	@Override
	protected void drawForeground(DrawContext context, int mouseX, int mouseY) {
		super.drawForeground(context, mouseX, mouseY);
		scrollBar();
		tradeButtons();
	}

	protected void tradeButtons() {
		int x = (width/2 - 378/2) + 5;
		int y = ((height - backgroundHeight) / 2) + 18;

		for (int i = 0; i < Trades.TRADES.size(); i++) {
			int buttonIndex = i;
			if (selected == buttonIndex) {
				addDrawableChild(new TexturedButtonWidget(x , y + buttonIndex*27, 82, 27, 0, buttonV + 27, 0,
						TEXTURE, 512, 256, button -> {
					highlightButton(Trades.TRADES.get(buttonIndex), buttonIndex);
				}));
				addDrawable(new IconWidget(x, y + i*27, 16, 16, Trades.TRADES.get(i).getTexture()));
			} else {
				addDrawableChild(new TexturedButtonWidget(x , y + i*27, 82, 27, 0, buttonV, 0,
						TEXTURE, 512, 256, button -> {
					highlightButton(Trades.TRADES.get(buttonIndex), buttonIndex);
				}));
				addDrawable(new IconWidget(x, y + i*27, 16, 16, Trades.TRADES.get(i).getTexture()));
			}
		}
	}
	protected void highlightButton(Trade trade, int i) {
		if ( Trades.TRADES.get(i) == trade) {
			selected = i;
		}
	}

	protected void scrollBar() {
		addDrawableChild(new ScrollableWidget(width, height, width, height, Text.translatable("block.blockrail.trade_controller_block")) {
			@Override
			protected void appendClickableNarrations(NarrationMessageBuilder builder) {
				return;
			}

			@Override
			protected int getContentsHeight() {
				return Trades.TRADES.size()*27;
			}

			@Override
			protected double getDeltaYPerScroll() {
				return 27;
			}

			@Override
			protected void renderContents(DrawContext context, int mouseX, int mouseY, float delta) {
				int x = (width/2 - 378/2) + 5;
				int y = ((height - backgroundHeight) / 2) + 18;

				new TexturedButtonWidget(x, y, 20, 20, 0, 0, TEXTURE, button -> {
					System.out.println("sCrolling");
				});

			}
		});
	}

	@Override
	public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
		return super.mouseScrolled(mouseX, mouseY, amount);
	}

	@Override
	public void render(DrawContext context, int mouseX, int mouseY, float delta) {
		renderBackground(context);
		super.render(context, mouseX, mouseY, delta);
		drawMouseoverTooltip(context, mouseX, mouseY);
	}
}
