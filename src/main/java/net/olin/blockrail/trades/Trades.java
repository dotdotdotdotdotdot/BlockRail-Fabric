package net.olin.blockrail.trades;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.olin.blockrail.BlockRail;

import java.util.ArrayList;

public class Trades {
	public static ArrayList<Trade> TRADES = new ArrayList<>();
	public static final Trade ACACIA_BOAT = addTrades(Text.translatable("trade.blockrail.acacia_boat"), 6,
			new ItemStack(Items.ACACIA_BOAT), 1, new ItemStack(Items.EMERALD), new Identifier("minecraft", "textures/item/acacia_boat.png"));
	public static final Trade BOAT_TEST = addTrades(Text.translatable("trade.blockrail.acacia_boat"), 2,
			new ItemStack(Items.OAK_BOAT), 1, new ItemStack(Items.EMERALD), new Identifier("minecraft", "textures/item/acacia_boat.png"));
	private static void appendTrades(Trade trade) {
		TRADES.add(trade);
	}
	private static Trade addTrades(MutableText name, int cost, ItemStack itemCost, int reward, ItemStack itemReward, Identifier texture) {
		Trade trade = new Trade(name, cost, itemCost, reward, itemReward, texture);
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
