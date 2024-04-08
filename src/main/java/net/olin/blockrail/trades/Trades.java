package net.olin.blockrail.trades;

import net.minecraft.block.Block;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.olin.blockrail.BlockRail;

import java.util.ArrayList;

public class Trades {
	public static ArrayList<Trade> TRADES = new ArrayList<>();

	public static final Trade TRADE1 = addTrades(Text.translatable("trade.blockrail.trade1"), 1,
			new Identifier("minecraft", "textures/item/oak_boat.png"));
	public static final Trade TRADE2 = addTrades(Text.translatable("trade.blockrail.trade2"), 4,
			new Identifier("minecraft", "textures/item/acacia_boat.png"));
	public static final Trade TRADE3 = addTrades(Text.translatable("trade.blockrail.trade3"), 4,
			new Identifier("minecraft", "textures/item/oak_boat.png"));
	public static final Trade TRADE4 = addTrades(Text.translatable("trade.blockrail.trade4"), 4,
			new Identifier("minecraft", "textures/item/acacia_boat.png"));
	public static final Trade TRADE5 = addTrades(Text.translatable("trade.blockrail.trade5"), 4,
			new Identifier("minecraft", "textures/item/oak_boat.png"));
	public static final Trade TRADE6 = addTrades(Text.translatable("trade.blockrail.trade6"), 4,
			new Identifier("minecraft", "textures/item/acacia_boat.png"));

	private static void appendTrades(Trade trade) {
		TRADES.add(trade);
	}
	private static Trade addTrades(MutableText name, int cost, Identifier texture) {
		Trade trade = new Trade(name, cost, texture);
		appendTrades(trade);
        return trade;
    }

	@Override
	public String toString() {
		return TRADES.toString();
	}

	public static void registerTrades() {
		BlockRail.LOGGER.info("Loading trades for " + BlockRail.MOD_ID);
	}
}
