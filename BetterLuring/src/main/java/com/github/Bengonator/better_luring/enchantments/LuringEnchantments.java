package com.github.Bengonator.better_luring.enchantments;

import com.github.Bengonator.better_luring.inits.ItemsInit;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class LuringEnchantments extends Enchantment {

	static final EnchantmentCategory LURE_STICK_CATEGORY = EnchantmentCategory.create("lure_stick", item -> item == ItemsInit.LURE_STICK.get());

	public LuringEnchantments(Rarity rarity) {
		super(rarity, LURE_STICK_CATEGORY, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
	}

	@Override
	public int getMaxLevel() {
		return 3;
	}
}