package com.github.Bengonator.better_luring.init;

import com.github.Bengonator.better_luring.BetterLuring;
import com.github.Bengonator.better_luring.items.LureStick;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemsInit {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BetterLuring.MODID);

	public static final RegistryObject<Item> LURE_STICK = ITEMS.register("lure_stick", () ->
		new LureStick(new Item.Properties()
			.tab(CreativeModeTab.TAB_TOOLS)
			// todo was der Unterschied der zwei durabilities?
//					.durability(64)
			.defaultDurability(100)
//					.craftRemainder()
			//  todo wie bucket bei lava. wenn i zb an stick mach den ma mit zb essen befüllen muss
			//  dann kann i machen, dass ma den leeren stick griagt sobald de durability weg is
			//  natürli muss i dann crafting rezepte machen und ah schaun wegen dem, dass mei gegenstand ned breakt.

			// todo crafting rezept und wie repairen
		)
	);


}