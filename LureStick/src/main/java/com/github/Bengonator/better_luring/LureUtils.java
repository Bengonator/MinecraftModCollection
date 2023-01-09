package com.github.Bengonator.better_luring;

public class LureUtils {

	// region constants
	public static final int millisToSec = 1000;
	public static final int ticksPerSec = 20;
	public static final int millisPerTick = 1000 / ticksPerSec;
	public static int tickEventDelayInMs = 500;
	// endregion constants

	// region enchantment constants
	// todo für andere leute:
	//  crafting recipie machen
	//  texture verbessern oder was aus defaults herzaubern, so dass texture pack unabhängig is

	// todo vllt nu so dass enchantemnts gibt:
	//  itemStack.getAllEnchantments() (gibt viele ähnliche auch)
	//  vllt statt de werte unten arrays, wo zb das 0. default is, dann das 1. mit enchantment stufe 1, das 2. ...
	//  bzw allgemein natürlich irgendwass auslagern in config file
	//   wenn array, dann private und getter machen. oder normal lassen? bzw setter und falls null dann wieder default
	//  .
	//  duration: so dass länger dir nachgehen. dann eben soubnd pitchen oder kurzer sound
	//  range: afoch variable ändern
	//  speedModifier: afoch variable ändern
	//  unbreaking mit ein berechnen
	//  amount: wie viele folgen können. is wichtig falls ma 1000er mobs spawn. besseren namen überlegen für enchantment
	public static final int duration = 4; // wegen 4s Länge vom sound
	public static final int range = 16;
	public static final double speedModifier = 1.5;
	public static final int amount = 4;
	// endregion enchantment constants

	/*public static void makeMobsMoveTo(BlockPos to, Iterable<PathfinderMob> mobs, double speedModifier) {

		Iterator<PathfinderMob> mobsIter = mobs.iterator();

		int affectedMobs = 0;
		while (mobsIter.hasNext()) {

			if (affectedMobs == amount) break;

//					switch ()


			affectedMobs++;
			Executors.newSingleThreadExecutor().submit( () -> {
				for (int j = 1; j < duration * ticksPerSec; j++) {

					makeMobMoveTo(to, mobsIter.next(), speedModifier);

//					try {
//						wait(millisPerTick * 2); // todo wie oft senden?
//					} catch (InterruptedException ignored) {}
				}
			});
		}
	}*/

	/*	public static void makeMobMoveTo(BlockPos to, Mob mob, double speedModifier) {

		if (!mob.isAlive()) {
			System.out.println("\u001B[32m" + "dead: " + mob);
			return;
		}

		int x = to.getX();
		int y = to.getY();
		int z = to.getZ();

//		mob.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3(x, y, z));
		mob.getNavigation().moveTo(x, y, z, speedModifier);


//		switch (mob.getClass().getSimpleName()) {
//		}


	}*/
}