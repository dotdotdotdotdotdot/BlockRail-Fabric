package net.olin.blockrail.trades;

import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.util.Identifier;

public class Trade {
	private MutableText name;
	private int cost;
	private ItemStack itemCost;
	private int reward;
	private ItemStack itemReward;

	private final Identifier texture;
    public Trade(MutableText name, int cost, ItemStack itemCost, int reward, ItemStack itemReward, Identifier texture) {
		this.name = name;
		this.cost = cost;
		this.itemCost = itemCost;
		this.reward = reward;
		this.itemReward = itemReward;
		this.texture = texture;
	}
	public String getName() {
		return this.name.toString();
	}
	public int getCost() {
        return this.cost;
	}
	public String getStringCost() {
        return String.valueOf(this.cost);
	}
	public Identifier getTexture() {
		return this.texture;
	}
	public int getReward() {
		return this.reward;
	}
	public ItemStack getItemReward() {
		return this.itemReward;
	}
	public ItemStack getItemCost() {
		return this.itemCost;
	}
}

