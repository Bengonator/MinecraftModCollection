package com.github.Bengonator.minecraft_mod_collection.better_hoppers;

import com.github.Bengonator.minecraft_mod_collection.better_hoppers.init.ItemInit;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BetterHoppers.MODID)
public class BetterHoppers
{
    public static final String MODID = "better_hoppers";

    public BetterHoppers()
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