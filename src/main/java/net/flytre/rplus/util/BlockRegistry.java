package net.flytre.rplus.util;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.flytre.rplus.block.Diode;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockRegistry {

    public static final Block DIODE = new Diode(FabricBlockSettings.of(Material.SUPPORTED).breakInstantly().sounds(BlockSoundGroup.WOOD));


    public static void init() {
        Registry.register(Registry.BLOCK, new Identifier("rplus", "diode"), DIODE);
        Registry.register(Registry.ITEM, new Identifier("rplus", "diode"), new BlockItem(DIODE, new Item.Settings().group(ItemGroup.REDSTONE)));
    }
}
