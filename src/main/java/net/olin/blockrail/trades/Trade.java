package net.olin.blockrail.trades;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;


public class Trade {


    private int tradeId;
    private MutableText name;
    private ItemStack input;
    private int inputAmount;
    private ItemStack output;
    private int outputAmount;
    private Identifier texturePath;
    public Trade(int tradeId, String name, String input, int inputAmount, String output, int outputAmount, String namespace, String texturePath) {
        this.tradeId = tradeId;
        this.name = convertToMutable(name);
        this.input = convertToItem(input);
        this.inputAmount = inputAmount;
        this.output = convertToItem(output);
        this.outputAmount = outputAmount;
        this.texturePath = convertToIdentifier(namespace,texturePath);
    }


    public ItemStack convertToItem(String itemName) {
        String lowerItemName = itemName.toLowerCase();

        List<String> stringArray = new ArrayList<>();
        List<Item> itemArray = Registries.ITEM.stream().toList();
        for (Item item : itemArray) {
            stringArray.add(item.toString());
        }

        //System.out.println(stringArray.indexOf(lowerItemName));
        return new ItemStack(Item.byRawId(stringArray.indexOf(lowerItemName)));
    }

    public MutableText convertToMutable(String name){
        return Text.translatable(name);
    }

    public Identifier convertToIdentifier(String namespace, String path){
        return new Identifier(namespace, path);
    }

    public String inputAmountToString() {
        return String.valueOf(this.inputAmount);
    }


    public MutableText getName() {
        return name;
    }

    public void setName(String name) {
        this.name = convertToMutable(name);
    }

    public int getTradeId() {
        return tradeId;
    }

    public void setTradeId(int tradeId) {
        this.tradeId = tradeId;
    }

    public ItemStack getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = convertToItem(input);
    }

    public int getInputAmount() {
        return inputAmount;
    }

    public void setInputAmount(int inputAmount) {
        this.inputAmount = inputAmount;
    }

    public ItemStack getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = convertToItem(output);
    }

    public int getOutputAmount() {
        return outputAmount;
    }

    public void setOutputAmount(int outputAmount) {
        this.outputAmount = outputAmount;
    }

    public Identifier getTexturePath() {
        return texturePath;
    }

    public void setTexturePath(String namespace, String texturePath) {
        this.texturePath = convertToIdentifier(namespace,texturePath);
    }
}

