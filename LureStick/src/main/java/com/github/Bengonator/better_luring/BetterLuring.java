package com.github.Bengonator.better_luring;

import com.github.Bengonator.better_luring.init.ItemsInit;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
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

//        modEventBus.register(Lur);
        MinecraftForge.EVENT_BUS.register(this);
    }

//    @SubscribeEvent
//    public static void onPlayerInteract(PlayerInteractEvent event) {
//        System.out.println("You interacted with " + event.getItemStack());
//
////            Player player = event.getEntity();
////            player.sendSystemMessage(Component.literal("You interacted with " + event.getItemStack()));
//    }

//    @SubscribeEvent
//    public static void onLivingTickEvent(LivingEvent.LivingTickEvent event) {
//        System.out.println("tic event");
//
//
//    }
}