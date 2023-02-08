package com.github.Bengonator.better_luring;

import com.github.Bengonator.better_luring.inits.BlocksInit;
import com.github.Bengonator.better_luring.inits.EnchantmentsInit;
import com.github.Bengonator.better_luring.inits.ItemsInit;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static com.github.Bengonator.better_luring.BetterLuring.MODID;

@Mod(MODID)
@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BetterLuring {
    public static final String MODID = "better_luring";

    public BetterLuring() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ItemsInit.ITEMS.register(modEventBus);
        BlocksInit.BLOCKS.register(modEventBus);
        BlocksInit.BLOCK_ITEMS.register(modEventBus);
        BlocksInit.BLOCK_ENTITIES.register(modEventBus);
        EnchantmentsInit.ENCHANTMENTS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }
}