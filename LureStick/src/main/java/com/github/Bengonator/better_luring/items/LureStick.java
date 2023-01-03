package com.github.Bengonator.better_luring.items;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;

import java.util.List;
import java.util.concurrent.Executors;

public class LureStick extends Item {

		private final int duration = 4; // wegen 4s Länge vom sound
		private final int ticksPerSec = 20;

	public LureStick(Properties properties) {
		super(properties);
	}

	// todo vllt nu so dass enchantemnts gibt, so dass länger dir nachgehen

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
		player.getCooldowns().addCooldown(this, ticksPerSec * duration); // todo +1 vllt

		ItemStack itemStack = player.getItemInHand(interactionHand);
		int dmg = itemStack.getDamageValue();

		if (itemStack.getMaxDamage() - dmg <= 0) {
			player.playSound(SoundEvents.ITEM_BREAK, .5F, 1F);
		}
		else {
			// todo damit ma ned vom sound weglaufen kann, vllt lieber was kurzes nehmen
			player.playSound(SoundEvents.PORTAL_AMBIENT, 1F, 1F);
			itemStack.setDamageValue(dmg + 1);
//			itemStack.getAllEnchantments() // todo unbreaking, und 2 von mir, mit radius und vllt dauer

			TargetingConditions tarCon = TargetingConditions.DEFAULT;
			tarCon.ignoreLineOfSight();
			tarCon.ignoreInvisibilityTesting();
			int range = 16; // todo vlt enchantment für range (und ned für dauer oder für beides)
			double x = player.getX();
			double y = player.getY();
			double z = player.getZ();
			AABB area = new AABB(x - range, y - range, z - range,
					x + range, y + range, z + range);

			List<Mob> mobs = level.getNearbyEntities(Mob.class, tarCon, player, area);
			mobs.forEach(mob -> {

//				mob.getBrain().removeAllBehaviors();
//				mob.getBrain().setDefaultActivity(Activity.PANIC);

				Executors.newSingleThreadExecutor().submit(makeMobsFollow(player, mob));

			// todo irgendwie dass ma ja sagen kann wer folgen soll
			// todo vllt obergrenze, so dass ma maximal so und so viele entities behreschen kann. vllt ah des erhöhen mit enchantment

//
//				switch (mob.getName().getString()) {
//					case "Zombie" -> {
//					}
//					case "Cow" -> {
//					}
//					case "Villager" -> {
//					}

//						mob.moveRelative(2, new Vec3(1, 1, 0)); // glaub da gehts um die look Richtung
//						mob.move(MoverType.PISTON, new Vec3(5, 2, 5)); // Piston moved nur um 1, sonst auch eher teleport
//				        mob.moveTo(new Vec3(x, y, z)); // teleport
//						mob.absMoveTo(x, y, z); // teleport

//				}

				// todo tooltip erst mit shift und ah wenn shift right click dann gehens weg und ned zu dir

				// todo vllt canattack auf false setzten, da die mobs ja quasi in den bann gezogen wurden
				// todo aber muss am ende wieder auf true setzen, soferns vorher true war
			});

			// todo es gibt was mit .selector, oder vllt nimm i einfach a genauere class, je nachdem was leichter geht

		}


		// todo wird haltbarkeit automatisch angewendet?
		// todo NEIN!!!

		return super.use(level, player, interactionHand);
	}

	@Override
	public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {

		final long WINDOW = Minecraft.getInstance().getWindow().getWindow();

		if (InputConstants.isKeyDown(WINDOW, GLFW.GLFW_KEY_LEFT_SHIFT)
				|| InputConstants.isKeyDown(WINDOW, GLFW.GLFW_KEY_RIGHT_SHIFT)) {
			components.add(Component.literal("right-click to make entities go towards you"));
		}

		super.appendHoverText(itemStack, level, components, tooltipFlag);
	}

	public Runnable makeMobsFollow(Player player, Mob mob) {
		return () -> {
			for (int i = 0; i < ticksPerSec * duration; i++) { // weil sound 4 sekunden lang ist
				double x = player.getX();
				double y = player.getY();
				double z = player.getZ();

				double speed = 0.1;
				double dX = x > mob.getX() ? speed : -speed;
				double dY = y > mob.getY() ? speed : -speed;
				double dZ = z > mob.getZ() ? speed : -speed;

				mob.lookAt(EntityAnchorArgument.Anchor.FEET, new Vec3(x, y, z));
				mob.setDeltaMovement(new Vec3(dX, dY, dZ));
//				mob.goalSelector.removeAllGoals();
				mob.resetFallDistance();

				try {
					Thread.sleep(1000 / ticksPerSec);
				} catch (InterruptedException e) {
					throw new RuntimeException(e); // todo eig geht continue bzw no catch
				}
			}
		};
	}
}