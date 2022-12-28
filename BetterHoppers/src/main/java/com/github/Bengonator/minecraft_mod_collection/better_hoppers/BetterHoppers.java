package com.github.Bengonator.minecraft_mod_collection.better_hoppers;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(BetterHoppers.MODID)
public class BetterHoppers
{
    public static final String MODID = "better_hoppers";

    public BetterHoppers()
    {
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    public static class TestModEvents {
        @SubscribeEvent
        public static void onPlayerInteract(PlayerInteractEvent event) {
            Player player = event.getEntity();

            player.sendSystemMessage(Component.literal("Your interacted with " + event.getItemStack()));
        }
    }
}