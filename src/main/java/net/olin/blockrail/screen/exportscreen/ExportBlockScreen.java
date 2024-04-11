package net.olin.blockrail.screen.exportscreen;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.olin.blockrail.BlockRail;

public class ExportBlockScreen extends HandledScreen<ExportBlockScreenHandler> {
	private static final Identifier TEXTURE = new Identifier(BlockRail.MOD_ID, "textures/gui/export_block_gui.png");
	public ExportBlockScreen(ExportBlockScreenHandler handler, PlayerInventory inventory, Text title) {
		super(handler, inventory, title);

	}

	@Override
	protected void init() {
		super.init();
	}

	@Override
	protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
		RenderSystem.setShader(GameRenderer::getPositionTexProgram);
		RenderSystem.setShaderColor(1f,1f,1f,1f);
		RenderSystem.setShaderTexture(0,TEXTURE);
		int x = (width - backgroundWidth) / 2;
		int y = (height - backgroundHeight) / 2;

		context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

		renderProgressArrow(context, x, y);
	}

	private void renderProgressArrow(DrawContext context, int x, int y) {
		if(handler.isTrading()) {
			context.drawTexture(TEXTURE, x + 50, y + 33, 176, 0, handler.getScaledProgress(), 17);
		}
	}

	@Override
	public void render(DrawContext context, int mouseX, int mouseY, float delta) {
		renderBackground(context);
		super.render(context, mouseX, mouseY, delta);
		drawMouseoverTooltip(context, mouseX, mouseY);
	}

}
