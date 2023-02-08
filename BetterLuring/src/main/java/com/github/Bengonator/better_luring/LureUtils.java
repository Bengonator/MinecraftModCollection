package com.github.Bengonator.better_luring;

import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LureUtils {

	// region constants
	public static final int TICKS_PER_SEC = 20;
	public static final float MILLIS_TO_TICKS = TICKS_PER_SEC / 1000F;
	public static final float OFFSET_TO_BLOCK_MIDDLE = 0.5F;

	// endregion constants

	// region enchantment settings
	// todo:
	//  in doku des enchantments erklären und nimma zu "future" tun
	//  crafting recipies machen
	//  texture verbessern oder was aus defaults herzaubern, so dass texture pack unabhängig is
	//  enchantments:
	//   theoretisch auslagern in config file und dann private machen und getter erstellen (glaub muss array kopieren, sonst useless)
	//   enchanted books als items? dann darauf achten, dass ma zb nicht einen meiner enchants mit einem Rüstungs-Enchant hat, was useless wäre.

	public static final Integer[] DURATION_ENCHANTMENT = new Integer[]{2, 4, 6, 8};
	public static final Integer[] AMOUNT_ENCHANTMENT = new Integer[]{4, 8, 16, 32};
	public static final Float[] RANGE_ENCHANTMENT = new Float[]{8F, 12F, 18F, 26F};
	public static final Float[] SPEED_ENCHANTMENT = new Float[]{1.5F, 2F, 2.5F, 3F};
	// endregion enchantment settings

	// region block settings
	public static final float CLIENT_TICK_DELAY = 0.25F * TICKS_PER_SEC;
	public static final int BLOCK_DURATION = 1000; // milliseconds
	public static final int BLOCK_AMOUNT = AMOUNT_ENCHANTMENT[3];
	public static final float BLOCK_RANGE = RANGE_ENCHANTMENT[1];
	public static final float BLOCK_SPEED = 0.75F;
	// endregion block settings

	public static void createLureParticles(Level level, double x, double y, double z, double reducedHeight, int nStrains, int nParticles) {
		RandomSource rnd = level.getRandom();

		float spread = 0.1F;                                                // spread of particles for x- and z-axis
		for (int strain = 0; strain < nStrains; strain++) {                 // amount of particle-strains
			for (int particle = 0; particle < nParticles; particle++) {     // amount of particles per particle-strain

				level.addParticle(
					// small short green particles
					ParticleTypes.COMPOSTER,

					// -spread till +spread
					x + (particle * rnd.nextFloat() * spread) * (rnd.nextBoolean() ? -1 : +1),

					// 1/nParticles is the factor, which make the highest particle just lower than one block high.
					y + particle * ((1F-reducedHeight) / nParticles),

					// -spread till +spread
					z + (particle * rnd.nextFloat() * spread) * (rnd.nextBoolean() ? -1 : +1),

					// speed of particles
					rnd.nextGaussian() * 0.02,      // x-axis
					rnd.nextGaussian() * 0.02,      // y-axis
					rnd.nextGaussian() * 0.02       // z-axis
				);
			}
		}

	}

	public static void lureMob(Mob mob, Vec3 lookAt, Vec3 moveTo, int duration, float speed) {

		/*
			todo vllt villagers extra beobachten
			 mob.getBrain().removeAllBehaviors();
			 mob.getBrain().setDefaultActivity(Activity.PANIC);
			 ghasts sind auch komiscch, vllt "alle" testen und jene einzeln debuggen und schaun ob goals exisitieren
		*/
		GoalSelector goalSelector = mob.goalSelector;

		List<WrappedGoal> runningGoals = goalSelector.getRunningGoals().toList();
		runningGoals.forEach(WrappedGoal::stop);

		List<WrappedGoal> goals = goalSelector.getAvailableGoals().stream().toList();
		goalSelector.removeAllGoals();

		Executors.newSingleThreadScheduledExecutor().schedule(
			() -> {
				for (WrappedGoal goal : goals) {
					goalSelector.addGoal(goal.getPriority(), goal.getGoal());
				}
			},
			duration,
			TimeUnit.MILLISECONDS);

		mob.lookAt(EntityAnchorArgument.Anchor.EYES, lookAt);
		mob.getNavigation().moveTo(moveTo.x, moveTo.y, moveTo.z, speed);
	}
}