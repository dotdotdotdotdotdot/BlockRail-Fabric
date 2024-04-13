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

public class Trades2 {


    public static ArrayList<Trade2> TRADES = new ArrayList<>();
    public static void appendTrades(Trade2 trade) {
        TRADES.add(trade);
        System.out.println(TRADES.size());
    }

    public static Trade2 addTrades(int tradeId, String name, String input, int inputAmount, String output, int outputAmount, String texturePath) {
        Trade2 trade = new Trade2(tradeId, name, input, inputAmount, output, outputAmount, texturePath);
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
