package com.github.Bengonator.minecraft_mod_collection.better_hoppers.init;

import com.github.Bengonator.minecraft_mod_collection.better_hoppers.BetterHoppers;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BetterHoppers.MODID);

	// todo testen ob leerzeichen erlaubt sind im namen
	public static final RegistryObject<Item> LURE_STICK = ITEMS.register("lure_stick",
			() -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));


}