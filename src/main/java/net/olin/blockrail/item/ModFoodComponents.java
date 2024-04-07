package net.olin.blockrail.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.PotionItem;

public class ModFoodComponents {
	public static final FoodComponent BUCKET_OF_BEER = new FoodComponent.Builder().hunger(8).saturationModifier(0.3f)
			.statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 60), 0.20f).build();

}
