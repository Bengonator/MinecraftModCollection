package com.github.Bengonator.better_luring;

import com.github.Bengonator.better_luring.inits.BlocksInit;
import com.github.Bengonator.better_luring.inits.EnchantmentsInit;
import com.github.Bengonator.better_luring.inits.ItemsInit;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static com.github.Bengonator.better_luring.BetterLuring.MOD_ID;

@Mod(MOD_ID)
@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BetterLuring {
    public static final String MOD_ID = "better_luring";

    public BetterLuring() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ItemsInit.ITEMS.register(modEventBus);
        BlocksInit.BLOCKS.register(modEventBus);
        BlocksInit.BLOCK_ITEMS.register(modEventBus);
        BlocksInit.BLOCK_ENTITIES.register(modEventBus);
        EnchantmentsInit.ENCHANTMENTS.register(modEventBus);

        modEventBus.addListener(this::buildCreativeTabContents);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void buildCreativeTabContents(CreativeModeTabEvent.BuildContents event) {

        CreativeModeTab tab = event.getTab();

        if (tab == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(new ItemStack(ItemsInit.LURE_STICK.get()));

        } else if (tab == CreativeModeTabs.REDSTONE_BLOCKS) {
            event.accept(new ItemStack(BlocksInit.LURE_BLOCK_ITEM.get()));
        }
    }
}