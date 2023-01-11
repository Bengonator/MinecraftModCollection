package com.github.Bengonator.better_luring.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.particle.SimpleAnimatedParticle;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.*;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.github.Bengonator.better_luring.LureUtils.*;

public class LureStick extends Item {

	public LureStick(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {

		// + 1, so that there is some safety time between two uses of the item.
		player.getCooldowns().addCooldown(this, ticksPerSec * duration + 1);
		ItemStack itemStack = player.getItemInHand(interactionHand);
		int takenDmg = itemStack.getDamageValue();
		int remainingDmg = itemStack.getMaxDamage() - takenDmg;

		double playerX = player.getX();
		double playerY = player.getY();
		double playerZ = player.getZ();

		BlockHitResult hitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.NONE);
		double hitX = hitResult.getLocation().x;
		double hitY = hitResult.getLocation().y;
		double hitZ = hitResult.getLocation().z;

		if (level.isClientSide) {
			if (remainingDmg > 0) {

				// todo wenn block drüber
				//  was wenn seitlich oder sogar unten berühren?
				//  wie mit non solids umgehen? einfach so wie jetzt, also innen spawnen?
//				BlockState blockState = level.getBlockState(hitResult);
				double offsetForTopOfBlock = 0;
//				if (blockState.isSolidRender(level, hitResult)) {
//					offsetForTopOfBlock = blockState.getShape(level, hitResult).max(Direction.Axis.Y, 0.5, 0.5) + 0.03125;
//				}

				Random rnd = new Random();
				float spread = 0.2F;
//				rnd.nextFloat(-spread, +spread)

				RandomSource randomsource = level.getRandom();
				for(int times = 0; times < 5; times++) {
					for (int i = 0; i < 5; i++) {

						level.addParticle(
							ParticleTypes.COMPOSTER,
							hitX + i * rnd.nextFloat(-spread, +spread),
							hitY + i * 0.2,
							hitZ + i * rnd.nextFloat(-spread, +spread),
							randomsource.nextGaussian() * 0.02,
							randomsource.nextGaussian() * 0.02,
							randomsource.nextGaussian() * 0.02);
					}
				}
			}
			else {
				player.playSound(SoundEvents.ITEM_BREAK, .5F, 1F);
			}
		}
		else {
			if (remainingDmg > 0) {

				TargetingConditions tarCon = TargetingConditions.DEFAULT
					.ignoreLineOfSight()
					.ignoreInvisibilityTesting();

				int affected = 0;
				for (Mob mob : level.getNearbyEntities(Mob.class, tarCon, player, player.getBoundingBox().inflate(range))) {

					if (affected == amount || affected == remainingDmg) break;

					/* If there is a custom name,
					   create the White- or Blacklist
					   and skip this mob,
					     if it is a Whitelist and the name is not contained
					     or if it is a Blacklist and the name is contained.
					*/
					if (itemStack.hasCustomHoverName()) {

						// region create White- or Blacklist
						ArrayList<String> split = new ArrayList<>(List.of(itemStack.getHoverName().getString().trim().split(";")));

						String start = split.get(0);
						boolean isWhitelist = true;

						if (start.charAt(0) == '+') {
							split.set(0, start.substring(1).trim());

						} else if (start.charAt(0) == '-') {
							isWhitelist = false;
							split.set(0, start.substring(1).trim());
						}

						for (int i = 1; i < split.size(); i++) {
							split.set(i, split.get(i).trim());
						}
						// endregion create Whit- or Blacklist

						boolean contains = split.contains(mob.getDisplayName().getString());
						if ( (isWhitelist && !contains) || (!isWhitelist && contains) ) {
							continue;
						}
					}

					// region affect mobs
					affected++;

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
						TimeUnit.SECONDS);

					mob.lookAt(
						EntityAnchorArgument.Anchor.EYES,
						new Vec3(playerX, playerY, playerZ));

					mob.getNavigation().moveTo(hitX, hitY, hitZ, speedModifier);

					itemStack.setDamageValue(takenDmg + affected);
					// endregion affect mobs
				}
			}
		}

		return super.use(level, player, interactionHand);
	}

/*	@Override
	public InteractionResult interactLivingEntity(ItemStack p_41398_, Player p_41399_, LivingEntity p_41400_, InteractionHand p_41401_) {
		// todo wenn i später das machen will mit tiereauswählen per rechts click und wegwählen per shift rechts click oder so

		return super.interactLivingEntity(p_41398_, p_41399_, p_41400_, p_41401_);
	}*/

	@Override
	public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {

		if (Screen.hasShiftDown()) {
			components.add(Component.literal("right-click to lure mobs"));
		}

		super.appendHoverText(itemStack, level, components, tooltipFlag);
	}
}