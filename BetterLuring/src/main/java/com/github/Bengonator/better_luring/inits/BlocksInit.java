package com.github.Bengonator.better_luring.inits;

import com.github.Bengonator.better_luring.BetterLuring;
import com.github.Bengonator.better_luring.blocks.LureBlock;
import com.github.Bengonator.better_luring.blocks.block_entities.LureBlockEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlocksInit {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BetterLuring.MODID);
	public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BetterLuring.MODID);

	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, BetterLuring.MODID);

	public static final RegistryObject<Block> LURE_BLOCK = BLOCKS.register("lure_block", () ->
		new LureBlock(BlockBehaviour.Properties.of(Material.METAL))
	);

	public static final RegistryObject<Item> LURE_BLOCK_ITEM = BLOCK_ITEMS.register("lure_block", () ->
		new BlockItem(LURE_BLOCK.get(), new Item.Properties()
			.tab(CreativeModeTab.TAB_REDSTONE)
		)
	);

	@SuppressWarnings("DataFlowIssue")
	public static final RegistryObject<BlockEntityType<LureBlockEntity>> LURE_BLOCK_ENTITY = BLOCK_ENTITIES.register("lure_block", () ->
		BlockEntityType.Builder.of(LureBlockEntity::new, BlocksInit.LURE_BLOCK.get()).build(null)
	);
}