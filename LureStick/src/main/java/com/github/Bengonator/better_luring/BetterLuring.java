package com.github.Bengonator.better_luring;

import com.github.Bengonator.better_luring.init.ItemInit;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BetterLuring.MODID)
public class BetterLuring
{
    public static final String MODID = "better_luring";

    public BetterLuring()
    {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ItemInit.ITEMS.register(modEventBus);


        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    public static class TestModEvents {
        @SubscribeEvent
        public static void onPlayerInteract(PlayerInteractEvent event) {
//            System.out.println("You interacted with " + event.getItemStack());


//            Player player = event.getEntity();
//            player.sendSystemMessage(Component.literal("You interacted with " + event.getItemStack()));
        }
    }
}