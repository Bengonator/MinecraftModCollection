package com.github.Bengonator.better_luring.inits;

import com.github.Bengonator.better_luring.BetterLuring;
import com.github.Bengonator.better_luring.items.LureStick;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemsInit {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BetterLuring.MOD_ID);

	public static final RegistryObject<Item> LURE_STICK = ITEMS.register("lure_stick", () ->
		new LureStick(new Item.Properties()
			.defaultDurability(100)
		)
	);
}