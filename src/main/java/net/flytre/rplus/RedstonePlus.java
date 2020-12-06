package net.flytre.rplus;

import net.fabricmc.api.ModInitializer;
import net.flytre.rplus.util.BlockRegistry;

public class RedstonePlus implements ModInitializer {

	@Override
	public void onInitialize() {
		System.out.println("Hello Fabric world!");
		BlockRegistry.init();
	}
}
