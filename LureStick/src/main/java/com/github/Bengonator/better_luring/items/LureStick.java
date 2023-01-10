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
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.github.Bengonator.better_luring.LureUtils.*;

public class LureStick extends Item {

	public LureStick(Properties properties) {
		super(properties);
	}

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
				// todo is sound nu da? habn nie gehört. aber lieber wegtun und dafür partikel, markeirt somit wo mobs hingehen
				player.playSound(SoundEvents.PORTAL_AMBIENT, 1F, 1F); // todo sound entfernen? oder eben was kurzes machen
				itemStack.setDamageValue(dmg + 1);

				TargetingConditions tarCon = TargetingConditions.DEFAULT
					.ignoreLineOfSight()
					.ignoreInvisibilityTesting();

				level.getNearbyEntities(
					Mob.class, tarCon, player, player.getBoundingBox().inflate(range))
					.forEach( mob -> {

					// todo amount nicht vergessen, es soll aber nur hoch gezählt werden, wenn es ein tier ist das beeinflusst wird
					// also im bezug auf whitelist. was mit ghast, ...?? weiß nu ned

					// todo aus namen auslesen und danach entscheiden, welche mobs angezogen werden
					//  vllt mit + - am anfang für black oder whitelist
					//  dann ; trennt alles
					//  mob.getClass().getSimpleName()
					//  solange keine wildcards oder gruppen (friedlich, monster,...) brauch ich nur einmal + oder - checken

					// todo vllt villagers extra beobachten
					//  mob.getBrain().removeAllBehaviors();
					//  mob.getBrain().setDefaultActivity(Activity.PANIC);
					//  ghasts sind auch komiscch, vllt "alle" testen und jene einzeln debuggen und schaun ob goals exisitieren
					//  was mit fische?

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
			}
		}

		return super.use(level, player, interactionHand);
	}

	@Override
	public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {

		// todo testen ob beide shift tasten
		if (Screen.hasShiftDown()) {
			components.add(Component.literal("right-click to make entities move towards you"));
		}

		super.appendHoverText(itemStack, level, components, tooltipFlag);
	}
}