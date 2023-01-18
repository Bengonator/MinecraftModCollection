package com.github.Bengonator.better_luring;

import com.github.Bengonator.better_luring.init.ModItems;
import com.github.Bengonator.better_luring.items.LureStick;
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

        ModItems.ITEMS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
//        MinecraftForge.EVENT_BUS.register(LureStick.class);
    }
}