package net.olin.blockrail.trades;

import net.minecraft.text.MutableText;
import net.minecraft.util.Identifier;

public class Trade {


	private MutableText name;
	private int cost;
	private int reward;
	private final Identifier texture;
    public Trade(MutableText name, int cost, Identifier texture) {
		this.name = name;
		this.cost = cost;
		this.reward = 0;
		this.texture = texture;
	}

	public MutableText getName() {
		return this.name;
	}

	public int getCost() {
		return this.cost;
	}

	public Identifier getTexture() {
		return this.texture;
	}

	public int getReward() {
		return this.reward;
	}

}

