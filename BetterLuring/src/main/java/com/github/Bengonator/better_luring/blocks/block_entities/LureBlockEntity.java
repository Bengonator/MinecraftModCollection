package com.github.Bengonator.better_luring.blocks.block_entities;

import com.github.Bengonator.better_luring.inits.BlocksInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import static com.github.Bengonator.better_luring.LureUtils.*;

public class LureBlockEntity extends BlockEntity {

	private int clientTimer = 0;
	private int serverTimer = 0;

	public LureBlockEntity(BlockPos blockPos, BlockState blockState) {
		super(BlocksInit.LURE_BLOCK_ENTITY.get(), blockPos, blockState);
	}


	public static <T extends BlockEntity> void tick(Level level, BlockPos blockPos, BlockState blockState, T blockEntity) {
		LureBlockEntity lbEntity = (LureBlockEntity) blockEntity;

		if (!level.hasNeighborSignal(blockPos)) return;

		if (level.isClientSide) {
			if (lbEntity.clientTimer < CLIENT_TICK_DELAY) {
				lbEntity.clientTimer++;
				return;
			}
			lbEntity.clientTimer = 0;

			float offsetToMiddle = OFFSET_TO_BLOCK_MIDDLE;
			float offsetToTop = 1.2F;

			createLureParticles(level,
				blockPos.getX() + offsetToMiddle,
				blockPos.getY() + offsetToTop,
				blockPos.getZ() + offsetToMiddle,
				offsetToTop-1,
				5,
				5
			);
		}
		else {
			if (lbEntity.serverTimer < BLOCK_DURATION * MILLIS_TO_TICKS) { // 500ms --> 10 Ticks
				lbEntity.serverTimer++;
				return;
			}
			lbEntity.serverTimer = 0;

			Vec3 vecCords = new Vec3(blockPos.getX(), blockPos.getY(), blockPos.getZ());
			int affected = 0;
			for (Mob mob : level.getEntitiesOfClass(Mob.class, new AABB(blockPos).inflate(BLOCK_RANGE))) {

				if (affected == BLOCK_AMOUNT || !mob.isAlive()) break;
				affected++;

				lureMob(mob,
					vecCords.add(0, 1, 0),     // look at the particles above the block
					vecCords,
					BLOCK_DURATION, // milliseconds
					BLOCK_SPEED
				);
			}
		}
	}
}