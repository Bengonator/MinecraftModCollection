package com.github.Bengonator.better_luring;

public class LureUtils {

	// region constants
//	public static final int millisToSec = 1000;
	public static final int ticksPerSec = 20;
//	public static final int millisPerTick = 1000 / ticksPerSec;
//	public static int tickEventDelayInMs = 500;
	// endregion constants

	// region enchantment constants
	// todo:
	//  crafting recipies machen
	//  texture verbessern oder was aus defaults herzaubern, so dass texture pack unabhängig is
	//  enchantments:
	//   theoretisch auslagern in config file und dann private machen und getter erstellen (glaub muss array kopieren, sonst useless)
	//   enchanted books als items? dann darauf achten, dass ma zb nicht einen meiner enchants mit einem Rüstungs-Enchant hat, was useless wäre.

	public static final Integer[] durationEnchantment = new Integer[]{2, 4, 6, 8};
	public static final Integer[] amountEnchantment = new Integer[]{4, 8, 16, 32};
	public static final Float[] rangeEnchantment = new Float[]{8F, 12F, 18F, 26F};
	public static final Float[] speedEnchantment = new Float[]{1.5F, 2F, 2.5F, 3F};
	// endregion enchantment constants
}