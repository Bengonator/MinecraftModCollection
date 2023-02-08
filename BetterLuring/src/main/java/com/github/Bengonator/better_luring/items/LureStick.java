package com.github.Bengonator.better_luring.items;

import com.github.Bengonator.better_luring.inits.EnchantmentsInit;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static com.github.Bengonator.better_luring.LureUtils.*;

public class LureStick extends Item {

	public LureStick(Properties properties) {
		super(properties);
	}

	@Override
	public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, @NotNull InteractionHand interactionHand) {

		final ItemStack itemStack = player.getItemInHand(interactionHand);

		// region enchantment levels
		int durationLvl = itemStack.getEnchantmentLevel(EnchantmentsInit.DURATION.get());
		int amountLvl = itemStack.getEnchantmentLevel(EnchantmentsInit.AMOUNT.get());
		int rangeLvl = itemStack.getEnchantmentLevel(EnchantmentsInit.Range.get());
		int speedLvl = itemStack.getEnchantmentLevel(EnchantmentsInit.Speed.get());

		int unbreakingLvl = itemStack.getEnchantmentLevel(Enchantments.UNBREAKING);
		// endregion enchantment levels

		// + 1, so that there is some safety time between two uses of the item.
		int duration = DURATION_ENCHANTMENT[durationLvl];
		player.getCooldowns().addCooldown(this, TICKS_PER_SEC * duration + 1);

		final int alreadyTakenDmg = itemStack.getDamageValue();
		final int remainingDmg = itemStack.getMaxDamage() - alreadyTakenDmg;

		RandomSource rnd = level.getRandom();

		final double playerX = player.getX();
		final double playerY = player.getY();
		final double playerZ = player.getZ();

		BlockHitResult hitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.NONE);
		final double hitX = hitResult.getLocation().x;
		final double hitY = hitResult.getLocation().y;
		final double hitZ = hitResult.getLocation().z;

		if (level.isClientSide) {
			if (remainingDmg > 0) {

				float offsetValue = 0.25F;
				float hitXOffset = 0;
				float hitZOffset = 0;

				BlockPos hitBlockPos = hitResult.getBlockPos();
				Direction hitDirection = hitResult.getDirection();
				if (!level.getBlockState(hitBlockPos).isAir()) {
					switch (hitDirection) {
						case NORTH -> hitZOffset = -offsetValue;
						case EAST -> hitXOffset = offsetValue;
						case SOUTH -> hitZOffset = offsetValue;
						case WEST -> hitXOffset = -offsetValue;
					}
				}

				createLureParticles(level,
					hitX + hitXOffset,
					hitY + (hitDirection == Direction.DOWN ? -1 : 0),
					hitZ + hitZOffset,
					0,
					5,
					5
				);
			}
			else {
				player.playSound(SoundEvents.ITEM_BREAK, .5F, 1F);
			}
		}
		else {
			if (remainingDmg > 0) {

				TargetingConditions tarCon = TargetingConditions.DEFAULT
					// Todo, vllt des line of sight wegtun, dann nimmer des problem mit den underground mobs die reagieren,
					//  aber schaun, weil vllt gehts dann nd wenns weggedreht sind
					//  spannend: hat irgendwie auch funktioniert, auch wenns i hinter blöcken versteckt war. vllt numoi testen
					.ignoreLineOfSight()
					.ignoreInvisibilityTesting();

				int affected = 0;
				int affectedAfterUnbreaking = 0;

				// todo vllt ned afoch inflaten sondern zb mehr links rechts, aber weniger oben unten
				for (Mob mob : level.getNearbyEntities(Mob.class, tarCon, player, player.getBoundingBox().inflate(RANGE_ENCHANTMENT[rangeLvl]))) {

					if (affected == AMOUNT_ENCHANTMENT[amountLvl] || affected == remainingDmg || !mob.isAlive()) break;

					/* If there is a custom name,
					   create the White- or Blacklist
					   and skip this mob,
					     if it is a Whitelist and the name is not contained
					     or if it is a Blacklist and the name is contained.
					*/
					if (itemStack.hasCustomHoverName()) {

						// region create White- or Blacklist
						ArrayList<String> split = new ArrayList<>(List.of(itemStack.getHoverName().getString().trim().split(";")));
						split.removeIf(String::isBlank);

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

					/* Take the enchantment 'Unbreaking' into account:
					 * Don't reduce the durability, if the random float between 0 and 1 is larger than the percentage.
					 *
					 * Percentages:
					 * No enchantment: 1 / (1 + 0) = 1 / 1 = 1
					 * Unbreaking I:   1 / (1 + 1) = 1 / 2 = 0.5
					 * No enchantment: 1 / (1 + 2) = 1 / 3 = 0.34
					 * No enchantment: 1 / (1 + 3) = 1 / 4 = 0.25
					 */
					if (rnd.nextFloat() <= (1F / (1 + unbreakingLvl))) {
						affectedAfterUnbreaking++;
					}

					lureMob(mob,
						new Vec3(playerX, playerY, playerZ),
						new Vec3(hitX, hitY, hitZ),
						duration * 1000, // times 1000, as the time unit is milliseconds
						SPEED_ENCHANTMENT[speedLvl]
					);

					itemStack.setDamageValue(alreadyTakenDmg + affectedAfterUnbreaking);
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
	public void appendHoverText(@NotNull ItemStack itemStack, @Nullable Level level, @NotNull List<Component> components, @NotNull TooltipFlag tooltipFlag) {

		if (Screen.hasShiftDown()) {
			components.add(Component.literal("right-click to lure mobs"));
		}

		super.appendHoverText(itemStack, level, components, tooltipFlag);
	}

	@Override
	public int getEnchantmentValue(ItemStack stack) {
		return 20;
	}
}