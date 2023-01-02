package com.github.Bengonator.better_luring.init;

import com.github.Bengonator.better_luring.BetterLuring;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BetterLuring.MODID);

	// todo testen ob leerzeichen erlaubt sind im namen
	public static final RegistryObject<Item> LURE_STICK = ITEMS.register("lure_stick",
			() -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));


}