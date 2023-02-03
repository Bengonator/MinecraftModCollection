package com.github.Bengonator.better_luring.init;

import com.github.Bengonator.better_luring.BetterLuring;
import com.github.Bengonator.better_luring.enchantments.LuringEnchantments;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EnchantmentsInit {

	public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, BetterLuring.MODID);

	public static final RegistryObject<Enchantment> DURATION = ENCHANTMENTS.register("duration", () ->
		new LuringEnchantments(Enchantment.Rarity.COMMON)
	);

	public static final RegistryObject<Enchantment> AMOUNT = ENCHANTMENTS.register("amount", () ->
		new LuringEnchantments(Enchantment.Rarity.UNCOMMON)
	);

	public static final RegistryObject<Enchantment> Range = ENCHANTMENTS.register("range", () ->
		new LuringEnchantments(Enchantment.Rarity.UNCOMMON)
	);

	public static final RegistryObject<Enchantment> Speed = ENCHANTMENTS.register("speed", () ->
		new LuringEnchantments(Enchantment.Rarity.VERY_RARE)
	);

}