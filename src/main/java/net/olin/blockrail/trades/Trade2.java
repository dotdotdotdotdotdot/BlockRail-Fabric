package net.olin.blockrail.trades;

import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.MutableText;
import net.minecraft.util.Identifier;

import java.util.Objects;

public class Trade2 {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTradeId() {
        return tradeId;
    }

    public void setTradeId(int tradeId) {
        this.tradeId = tradeId;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public int getInputAmount() {
        return inputAmount;
    }

    public void setInputAmount(int inputAmount) {
        this.inputAmount = inputAmount;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public int getOutputAmount() {
        return outputAmount;
    }

    public void setOutputAmount(int outputAmount) {
        this.outputAmount = outputAmount;
    }

    public String getTexturePath() {
        return texturePath;
    }

    public void setTexturePath(String texturePath) {
        this.texturePath = texturePath;
    }

    private int tradeId;
    private String name;
    private String input;
    private int inputAmount;
    private String output;
    private int outputAmount;
    private String texturePath;
    public Trade2(int tradeId, String name, String input, int inputAmount, String output, int outputAmount, String texturePath) {
        this.tradeId = tradeId;
        this.name = name;
        this.input = input;
        this.inputAmount = inputAmount;
        this.output = output;
        this.outputAmount = outputAmount;
        this.texturePath = texturePath;
    }

    public ItemStack getItemStack(){
        if(Objects.equals(this.name, "EMERALD")){
            return new ItemStack(Items.EMERALD);
        }
        return null;
    }
}

