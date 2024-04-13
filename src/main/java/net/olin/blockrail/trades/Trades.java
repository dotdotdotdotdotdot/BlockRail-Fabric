package net.olin.blockrail.trades;

import net.olin.blockrail.BlockRail;

import java.util.ArrayList;

public class Trades {
    public static ArrayList<Trade> TRADES = new ArrayList<>();
    public static final Trade EMERALD = addTrades(0,"trade.blockrail.emerald","stone",10,"emerald",
            1,"minecraft","textures/item/emerald.png");
    public static final Trade EMERALD2 = addTrades(1,"trade.blockrail.emerald","stone",10,"emerald",
            1,"minecraft","textures/item/emerald.png");


    public static void appendTrades(Trade trade) {
        System.out.println(trade);
        try {
            TRADES.add(trade);
        }
        catch (Exception e) {
            System.out.println("Crashed here");
            System.out.println(e);
        }

        System.out.println(TRADES.size());
    }

    public static Trade addTrades(int tradeId, String name, String input, int inputAmount, String output, int outputAmount, String namespace, String texturePath) {
        Trade trade = new Trade(tradeId, name, input, inputAmount, output, outputAmount, namespace, texturePath);
        System.out.println(trade.getName());
        System.out.println(trade.getInput());
        System.out.println(trade.getInputAmount());
        System.out.println(trade.getOutput());
        System.out.println(trade.getOutputAmount());
        System.out.println(trade.getTexturePath());

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
