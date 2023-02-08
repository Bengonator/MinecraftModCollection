package com.github.Bengonator.better_luring.blocks;

import com.github.Bengonator.better_luring.blocks.block_entities.LureBlockEntity;
import com.github.Bengonator.better_luring.inits.BlocksInit;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEventListener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LureBlock extends Block implements EntityBlock {

	public LureBlock(Properties properties) {
		super(properties);
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
		return BlocksInit.LURE_BLOCK_ENTITY.get().create(blockPos, blockState);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState blockState, @NotNull BlockEntityType<T> blockEntityType) {
		return blockEntityType == BlocksInit.LURE_BLOCK_ENTITY.get() ? LureBlockEntity::tick : null;
	}

	@Nullable
	@Override
	public <T extends BlockEntity> GameEventListener getListener(@NotNull ServerLevel serverLevel, @NotNull T t) {
		return EntityBlock.super.getListener(serverLevel, t);
	}
}