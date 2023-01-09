package com.github.Bengonator.better_luring.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.github.Bengonator.better_luring.LureUtils.*;

public class LureStick extends Item {

	// region fields
//	private static long lastTickTime = 0;
//	private static List<Mob> affectedMobs;
//	private static Player player;
	// endregion fields

	public LureStick(Properties properties) {
		super(properties);
	}

	/*	@SubscribeEvent
	public static void onLivingTickEvent(LivingEvent.LivingTickEvent event) {

		long curTickTime = System.currentTimeMillis();
		if (curTickTime - lastTickTime < tickEventDelayInMs) return;
		lastTickTime = curTickTime;

		if (affectedMobs == null || event.getEntity() == null) return;

		for (Mob mob : affectedMobs) {
			mob.lookAt(
				EntityAnchorArgument.Anchor.EYES,
				new Vec3(player.getX(), player.getY(), player.getZ()));

			mob.getNavigation().moveTo(player, speedModifier);

			// todo crasht manchmal mit Nullpointer: mob.getNavigation().getPath().getNodeCount() oderso weil path is null
//				if (mob.getNavigation().getPath() == null) {
//					System.out.println("\u001B[31m" + "path null: " + mob.getName());
//				} else {
//					System.out.println("\u001B[32m" + "path not null: " + mob.getName());
//				}
		}
	}*/

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {

		player.getCooldowns().addCooldown(this, ticksPerSec * duration); // todo +1 vllt
		ItemStack itemStack = player.getItemInHand(interactionHand);
		int dmg = itemStack.getDamageValue();

		if (!level.isClientSide()) {
			if (itemStack.getMaxDamage() - dmg <= 0) {
				player.playSound(SoundEvents.ITEM_BREAK, .5F, 1F);

			} else {
				// todo damit ma ned vom sound weglaufen kann, vllt lieber was kurzes nehmen. besonders weil dann ah ka problem is mit längere durations
				//  vllt kurzer sound und dafür partikel während duration, somit keine probleme mit duration, und afoch partikel mitgeben, die an der blockpos gesetzt werden
				player.playSound(SoundEvents.PORTAL_AMBIENT, 1F, 1F); // todo sound entfernen? oder eben was kurzes machen
				itemStack.setDamageValue(dmg + 1);

				TargetingConditions tarCon = TargetingConditions.DEFAULT
					.ignoreLineOfSight()
					.ignoreInvisibilityTesting();

//				 todo funktioniert das noch mit mehreren playern?
//				LureStick.player = player;

				level.getNearbyEntities(
					Mob.class, tarCon, player, player.getBoundingBox().inflate(range))
					.forEach( mob -> {

						// todo aus namen auslesen und danach entscheiden, welche mobs angezogen werden
						//  vllt mit + - am anfang für black oder whitelist
						//  dann ; trennt alles
						//  mob.getClass().getSimpleName()
						// solange keine wildcards oder gruppen (friedlich, monster,...) brauch ich nur einmal + oder - checken

						// todo vllt villagers extra beobachten
						//  ghasts sind auch komicsch, vllt "alle" testen und jene einzeln debuggen und schaun ob goals exisitieren

						GoalSelector goalSelector = mob.goalSelector;

						List<WrappedGoal> runningGoals = goalSelector.getRunningGoals().toList();
						runningGoals.forEach(WrappedGoal::stop);

						List<WrappedGoal> goals = goalSelector.getAvailableGoals().stream().toList();
						goalSelector.removeAllGoals();

						Executors.newScheduledThreadPool(1).schedule(
							() -> {
								for (WrappedGoal goal : goals) {
									goalSelector.addGoal(goal.getPriority(), goal.getGoal());
								}
							},
							duration,
							TimeUnit.SECONDS);

						mob.lookAt(
							EntityAnchorArgument.Anchor.EYES,
							new Vec3(player.getX(), player.getY(), player.getZ()));

						mob.getNavigation().moveTo(player, speedModifier);
					});



	/*				Executors.newScheduledThreadPool(1).schedule(
					() -> { affectedMobs = null; },
					duration,
					TimeUnit.SECONDS);*/
			}


	/*				Iterator<Mob> mobs = level.getNearbyEntities(
					Mob.class, tarCon, player, player.getBoundingBox().inflate(range))
						.iterator();

				int affectedMobs = 0;
				while (mobs.hasNext()) {

					if (affectedMobs == amount) break;

//					switch ()

					affectedMobs++;


					Executors.newSingleThreadExecutor().submit( () -> {
						int delayFactor = 1;
						for (int j = 1; j < duration * ticksPerSec / delayFactor; j++) {

//							makeMobMoveTo(player.blockPosition(), mobs.next(), speedModifier);
							mobs.next().getNavigation().moveTo(player, speedModifier);

							try {
								wait(millisPerTick * delayFactor); // todo wie oft senden?
							} catch (InterruptedException ignored) {}
						}
					});
				}*/

				// region ideas that did not work
				// region moves
//					mob.moveRelative(2, new Vec3(1, 1, 0)); // glaub da gehts um die look Richtung
//					mob.move(MoverType.PISTON, new Vec3(5, 2, 5)); // Piston moved nur um 1, sonst auch eher teleport
//			        mob.moveTo(new Vec3(x, y, z)); // teleport
//					mob.absMoveTo(x, y, z); // teleport
				// endregion moves

				// region brain
//					mob.getBrain().removeAllBehaviors();
//					mob.getBrain().setDefaultActivity(Activity.PANIC);
				// endregion brain

				// region addGoal
//					mob.goalSelector.removeAllGoals();
//					mob.goalSelector.addGoal(1, new FleeSunGoal(mob, 5));
//					int a;
//					mob.aiStep();
//					a = 5;
//					mob.goalSelector.tick();
//					a = a + 3;
//					a = a + 30;


//					WrappedGoal[] goals = mob.goalSelector.getAvailableGoals().toArray(new WrappedGoal[0]);
//					mob.goalSelector.removeGoal(goals[0]);
//					mob.goalSelector.removeAllGoals();
//					mob.goalSelector.addGoal(0, new FollowMobGoal(mob, 3.0D, 3.0F, 7.0F));
				// endregion addGoal
				// endregion ideas that did not work
		}

		return super.use(level, player, interactionHand);
	}

	@Override
	public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {

		final long WINDOW = Minecraft.getInstance().getWindow().getWindow();

		if (Screen.hasShiftDown()) {
			components.add(Component.literal("right-click to make entities move towards you"));
		}

		super.appendHoverText(itemStack, level, components, tooltipFlag);
	}
}